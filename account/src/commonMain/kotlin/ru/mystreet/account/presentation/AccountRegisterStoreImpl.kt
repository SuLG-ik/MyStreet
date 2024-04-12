package ru.mystreet.account.presentation

import arrow.core.Ior
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.account.domain.entity.RegisterField
import ru.mystreet.account.domain.usecase.ProvideEmailUseCase
import ru.mystreet.account.domain.usecase.ProvideLoginUseCase
import ru.mystreet.account.domain.usecase.ProvideNameUseCase
import ru.mystreet.account.domain.usecase.ProvidePasswordRepeatUseCase
import ru.mystreet.account.domain.usecase.ProvidePasswordUseCase
import ru.mystreet.account.domain.usecase.RegisterIsContinueAvailableUseCase
import ru.mystreet.account.domain.usecase.RegisterUseCase

@Factory(binds = [AccountRegisterStore::class])
@OptIn(ExperimentalMviKotlinApi::class)
class AccountRegisterStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: AccountRegisterStore.SavedState,
    registerUseCase: RegisterUseCase,
    provideLoginUseCase: ProvideLoginUseCase,
    providePasswordUseCase: ProvidePasswordUseCase,
    provideEmailUseCase: ProvideEmailUseCase,
    providePasswordRepeatUseCase: ProvidePasswordRepeatUseCase,
    provideNameUseCase: ProvideNameUseCase,
    registerIsContinueAvailableUseCase: RegisterIsContinueAvailableUseCase,
) : AccountRegisterStore,
    Store<AccountRegisterStore.Intent, AccountRegisterStore.State, AccountRegisterStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "AccountRegisterStoreImpl",
        initialState = savedState.restore(
            provideLoginUseCase = provideLoginUseCase,
            providePasswordUseCase = providePasswordUseCase,
            provideEmailUseCase = provideEmailUseCase,
            providePasswordRepeatUseCase = providePasswordRepeatUseCase,
            provideNameUseCase = provideNameUseCase,
        ),
        reducer = {
            when (it) {
                Message.Loading -> copy(isLoading = true, isContinueAvailable = false)
                is Message.SetEmail -> {
                    val newField = field.copy(email = it.value)
                    copy(
                        field = newField,
                        isContinueAvailable = registerIsContinueAvailableUseCase(newField)
                    )
                }

                is Message.SetName -> {
                    val newField = field.copy(name = it.value)
                    copy(
                        field = newField,
                        isContinueAvailable = registerIsContinueAvailableUseCase(newField)
                    )
                }

                is Message.SetPassword -> {
                    val newField = field.copy(password = it.value)
                    copy(
                        field = newField,
                        isContinueAvailable = registerIsContinueAvailableUseCase(newField)
                    )
                }

                is Message.SetPasswordRepeat -> {
                    val newField = field.copy(passwordRepeat = it.value)
                    copy(
                        field = newField,
                        isContinueAvailable = registerIsContinueAvailableUseCase(newField)
                    )
                }

                is Message.SetLogin -> {
                    val newField = field.copy(login = it.value)
                    copy(
                        field = newField,
                        isContinueAvailable = registerIsContinueAvailableUseCase(newField)
                    )
                }
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<AccountRegisterStore.Intent.Continue> {
                val state = state()
                if (state.isLoading || !state.isContinueAvailable)
                    return@onIntent
                launch {
                    val response = registerUseCase(
                        username = state.field.login.getOrNull()!!,
                        password = state.field.password.getOrNull()!!,
                        email = state.field.email.getOrNull()!!,
                        name = state.field.name.getOrNull()!!
                    )
                    withContext(Dispatchers.Main) {
                        publish(AccountRegisterStore.Label.RegisterSuccess(response.username))
                    }
                }
            }
            onIntent<AccountRegisterStore.Intent.PasswordInput> {
                dispatch(Message.SetPassword(providePasswordUseCase(it.value)))
            }
            onIntent<AccountRegisterStore.Intent.PasswordRepeatInput> {
                dispatch(
                    Message.SetPasswordRepeat(
                        providePasswordRepeatUseCase(
                            value = it.value,
                            originalPassword = state().field.password.getOrNull() ?: ""
                        )
                    )
                )
            }
            onIntent<AccountRegisterStore.Intent.EmailInput> {
                dispatch(Message.SetEmail(provideEmailUseCase(it.value)))
            }
            onIntent<AccountRegisterStore.Intent.UsernameInput> {
                dispatch(Message.SetLogin(provideLoginUseCase(it.value)))
            }
            onIntent<AccountRegisterStore.Intent.LoginInput> {
                dispatch(Message.SetName(provideNameUseCase(it.value)))
            }
        },
    ) {

    sealed interface Message {
        data class SetLogin(
            val value: Ior<FieldError, String>,
        ) : Message

        data class SetEmail(
            val value: Ior<FieldError, String>,
        ) : Message

        data class SetName(
            val value: Ior<FieldError, String>,
        ) : Message

        data class SetPasswordRepeat(
            val value: Ior<FieldError, String>,
        ) : Message

        data class SetPassword(
            val value: Ior<FieldError, String>,
        ) : Message

        data object Loading : Message
    }

    override fun getSavedState(): AccountRegisterStore.SavedState {
        return AccountRegisterStore.SavedState(
            name = state.field.name.getOrNull() ?: "",
            email = state.field.email.getOrNull() ?: "",
            username = state.field.login.getOrNull() ?: "",
            password = state.field.password.getOrNull() ?: "",
            passwordRepeat = state.field.passwordRepeat.getOrNull() ?: "",
        )
    }


}


private fun AccountRegisterStore.SavedState.restore(
    provideLoginUseCase: ProvideLoginUseCase,
    providePasswordUseCase: ProvidePasswordUseCase,
    provideEmailUseCase: ProvideEmailUseCase,
    providePasswordRepeatUseCase: ProvidePasswordRepeatUseCase,
    provideNameUseCase: ProvideNameUseCase,
): AccountRegisterStore.State {
    return AccountRegisterStore.State(
        isLoading = false, isContinueAvailable = true, field = RegisterField(
            name = provideNameUseCase(name),
            login = provideLoginUseCase(username),
            email = provideEmailUseCase(email),
            password = providePasswordUseCase(password),
            passwordRepeat = providePasswordRepeatUseCase(passwordRepeat, password),
        )

    )
}