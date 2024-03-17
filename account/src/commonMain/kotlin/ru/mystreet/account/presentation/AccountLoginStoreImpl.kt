package ru.mystreet.account.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.account.domain.exception.UserIncorrectCredentials
import ru.mystreet.account.domain.usecase.LoginIsContinueAvailableUseCase
import ru.mystreet.account.domain.usecase.LoginUseCase
import ru.mystreet.account.domain.usecase.ProvideLoginUseCase
import ru.mystreet.account.domain.usecase.ProvidePasswordUseCase
import ru.mystreet.uikit.ValidatedField

@OptIn(ExperimentalMviKotlinApi::class)
class AccountLoginStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: AccountLoginStore.SavedState,
    loginUseCase: LoginUseCase,
    provideLoginUseCase: ProvideLoginUseCase,
    providePasswordUseCase: ProvidePasswordUseCase,
    loginIsContinueAvailableUseCase: LoginIsContinueAvailableUseCase,
) : AccountLoginStore,
    Store<AccountLoginStore.Intent, AccountLoginStore.State, AccountLoginStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "AccountLoginStoreImpl",
        initialState = savedState.restore(provideLoginUseCase, providePasswordUseCase),
        reducer = {
            when (it) {
                is Message.SetLogin -> {
                    val newField = field.copy(login = it.value)
                    copy(
                        isCredentialsIncorrect = false,
                        isContinueAvailable = loginIsContinueAvailableUseCase(newField),
                        field = newField,
                    )
                }

                is Message.SetPassword -> {
                    val newField = field.copy(password = it.value)
                    copy(
                        isCredentialsIncorrect = false,
                        isContinueAvailable = loginIsContinueAvailableUseCase(newField),
                        field = newField,
                    )
                }

                Message.Loading -> copy(isLoading = true, isContinueAvailable = false)
                Message.SetCredentialsIncorrect -> copy(
                    isLoading = false,
                    isContinueAvailable = false,
                    isCredentialsIncorrect = true,
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<AccountLoginStore.Intent.Continue> {
                val state = state()
                if (state.isLoading || !state.isContinueAvailable)
                    return@onIntent
                dispatch(Message.Loading)
                launch {
                    try {
                        val auth = loginUseCase(state.field.login.value, state.field.password.value)
                        withContext(Dispatchers.Main) {
                            publish(AccountLoginStore.Label.LoginSuccess(auth.username))
                        }
                    } catch (e: UserIncorrectCredentials) {
                        withContext(Dispatchers.Main) {
                            dispatch(Message.SetCredentialsIncorrect)
                        }
                    }
                }
            }
            onIntent<AccountLoginStore.Intent.LoginInput> { intent ->
                dispatch(Message.SetLogin(provideLoginUseCase(intent.value)))
            }
            onIntent<AccountLoginStore.Intent.PasswordInput> { intent ->
                dispatch(Message.SetPassword(providePasswordUseCase(intent.value)))
            }
        },
    ) {

    sealed interface Message {
        data class SetLogin(
            val value: ValidatedField<LoginField.FieldError>,
        ) : Message

        data class SetPassword(
            val value: ValidatedField<LoginField.FieldError>,
        ) : Message

        data object Loading : Message
        data object SetCredentialsIncorrect : Message
    }

    override fun getSavedState(): AccountLoginStore.SavedState {
        return AccountLoginStore.SavedState(
            login = state.field.login.value,
            password = state.field.password.value,
        )
    }

}


fun AccountLoginStore.SavedState.restore(
    provideLoginUseCase: ProvideLoginUseCase,
    providePasswordUseCase: ProvidePasswordUseCase
): AccountLoginStore.State {
    return AccountLoginStore.State(
        isLoading = false,
        isContinueAvailable = false,
        isCredentialsIncorrect = false,
        field = LoginField(
            login = provideLoginUseCase(login),
            password = providePasswordUseCase(password),
        )
    )
}