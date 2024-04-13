package ru.mystreet.account.presentation.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.usecase.LogOutUseCase
import ru.mystreet.core.component.onIntentWithSkipping

@Factory(binds = [AccountProfileSettingStore::class])
@OptIn(ExperimentalMviKotlinApi::class)
class AccountProfileSettingStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    logOutUseCase: LogOutUseCase,
) : AccountProfileSettingStore,
    Store<AccountProfileSettingStore.Intent, AccountProfileSettingStore.State, AccountProfileSettingStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "AccountProfileSettingStoreImpl",
        initialState = AccountProfileSettingStore.State(isLoading = false),
        reducer = {
            when (it) {
                else -> TODO()
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntentWithSkipping { intent: AccountProfileSettingStore.Intent.LogOut ->
                deferredLaunch {
                    logOutUseCase()
                    withContext(Dispatchers.Main) {
                        publish(AccountProfileSettingStore.Label.OnLogOut)
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {

    }
}