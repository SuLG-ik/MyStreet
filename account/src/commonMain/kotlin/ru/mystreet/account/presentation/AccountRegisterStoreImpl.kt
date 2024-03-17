package ru.mystreet.account.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.account.domain.entity.RegisterField
import ru.mystreet.account.domain.usecase.RegisterUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class AccountRegisterStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: AccountRegisterStore.SavedState,
    registerUseCase: RegisterUseCase,
) : AccountRegisterStore,
    Store<AccountRegisterStore.Intent, AccountRegisterStore.State, AccountRegisterStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "AccountRegisterStoreImpl",
        initialState = savedState.restore(),
        reducer = {
            when (it) {
                Message.Loading -> copy(isLoading = true, isContinueAvailable = false)
                is Message.SetEmail -> copy(field = field.copy(email = it.value))
                is Message.SetName -> copy(field = field.copy(name = it.value))
                is Message.SetPassword -> copy(field = field.copy(password = it.value))
                is Message.SetPasswordRepeat -> copy(field = field.copy(passwordRepeat = it.value))
                is Message.SetUsername -> copy(field = field.copy(username = it.value))
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<AccountRegisterStore.Intent.Continue> {
                val state = state()
                if (state.isLoading || !state.isContinueAvailable)
                    return@onIntent
                launch {
                    val response = registerUseCase(
                        username = state.field.username,
                        password = state.field.password,
                        email = state.field.email,
                        name = state.field.name
                    )
                    withContext(Dispatchers.Main) {
                        publish(AccountRegisterStore.Label.RegisterSuccess(response.username))
                    }
                }
            }
            onIntent<AccountRegisterStore.Intent.PasswordInput> {
                dispatch(Message.SetPassword(it.value))
            }
            onIntent<AccountRegisterStore.Intent.PasswordRepeatInput> {
                dispatch(Message.SetPasswordRepeat(it.value))
            }
            onIntent<AccountRegisterStore.Intent.EmailInput> {
                dispatch(Message.SetEmail(it.value))
            }
            onIntent<AccountRegisterStore.Intent.UsernameInput> {
                dispatch(Message.SetUsername(it.value))
            }
            onIntent<AccountRegisterStore.Intent.NameInput> {
                dispatch(Message.SetName(it.value))
            }
        },
    ) {

    sealed interface Message {
        data class SetUsername(
            val value: String,
        ) : Message

        data class SetEmail(
            val value: String,
        ) : Message

        data class SetName(
            val value: String,
        ) : Message

        data class SetPasswordRepeat(
            val value: String,
        ) : Message

        data class SetPassword(
            val value: String,
        ) : Message

        data object Loading : Message
    }

    override fun getSavedState(): AccountRegisterStore.SavedState {
        return AccountRegisterStore.SavedState(
            name = state.field.name,
            email = state.field.email,
            username = state.field.username,
            password = state.field.password,
            passwordRepeat = state.field.passwordRepeat,
        )
    }


}


private fun AccountRegisterStore.SavedState.restore(): AccountRegisterStore.State {
    return AccountRegisterStore.State(
        isLoading = false, isContinueAvailable = true, field = RegisterField(
            name = name,
            username = username,
            email = email,
            password = password,
            passwordRepeat = passwordRepeat
        )

    )
}