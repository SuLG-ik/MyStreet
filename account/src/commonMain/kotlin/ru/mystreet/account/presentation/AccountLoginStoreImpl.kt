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
import ru.mystreet.account.domain.usecase.LoginUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class AccountLoginStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: AccountLoginStore.SavedState,
    loginUseCase: LoginUseCase,
) : AccountLoginStore,
    Store<AccountLoginStore.Intent, AccountLoginStore.State, AccountLoginStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "AccountLoginStoreImpl",
        initialState = savedState.restore(),
        reducer = {
            when (it) {
                is Message.SetLogin ->
                    copy(field = field.copy(login = it.value))

                is Message.SetPassword ->
                    copy(field = field.copy(password = it.value))

                Message.Loading -> copy(isLoading = true, isContinueAvailable = false)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<AccountLoginStore.Intent.Continue> {
                val state = state()
                if (state.isLoading || !state.isContinueAvailable)
                    return@onIntent
                dispatch(Message.Loading)
                launch {
                    val auth = loginUseCase(state.field.login, state.field.password)
                    withContext(Dispatchers.Main) {
                        publish(AccountLoginStore.Label.LoginSuccess(auth.username))
                    }
                }
            }
            onIntent<AccountLoginStore.Intent.LoginInput> { intent ->
                dispatch(Message.SetLogin(intent.value))
            }
            onIntent<AccountLoginStore.Intent.PasswordInput> { intent ->
                dispatch(Message.SetPassword(intent.value))
            }
        },
    ) {

    sealed interface Message {
        data class SetLogin(
            val value: String,
        ) : Message

        data class SetPassword(
            val value: String,
        ) : Message

        data object Loading : Message
    }

    override fun getSavedState(): AccountLoginStore.SavedState {
        return AccountLoginStore.SavedState(
            login = state.field.login,
            password = state.field.password,
        )
    }

}


fun AccountLoginStore.SavedState.restore(): AccountLoginStore.State {
    return AccountLoginStore.State(
        isLoading = false,
        isContinueAvailable = false,
        field = LoginField(
            login = login,
            password = password
        )
    )
}