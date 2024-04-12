package ru.mystreet.account.presentation.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.usecase.LoadAccountProfileFullUseCase

@Factory(binds = [AccountProfileStore::class])
@OptIn(ExperimentalMviKotlinApi::class)
class AccountProfileStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    loadAccountProfileFullUseCase: LoadAccountProfileFullUseCase,
) : AccountProfileStore,
    Store<AccountProfileStore.Intent, AccountProfileStore.State, AccountProfileStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "AccountProfileStoreImpl",
        initialState = AccountProfileStore.State(),
        reducer = {
            when (it) {
                is Message.SetAccount -> copy(
                    isLoading = false,
                    account = it.account,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    try {
                        val account = loadAccountProfileFullUseCase()
                        withContext(Dispatchers.Main) {
                            dispatch(Message.SetAccount(account))
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            publish(AccountProfileStore.Label.OnNotAuthenticated)
                        }
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetAccount(
            val account: AccountProfileFull,
        ) : Message
    }
}