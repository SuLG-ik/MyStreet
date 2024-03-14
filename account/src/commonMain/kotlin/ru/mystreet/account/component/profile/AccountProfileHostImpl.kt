package ru.mystreet.account.component.profile

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.presentation.AccountProfileStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values

class AccountProfileHostImpl(
    diComponentContext: DIComponentContext,
    onNotAuthenticated: () -> Unit
) : AppComponentContext(diComponentContext), AccountProfileHost {

    private val scope = coroutineScope()

    private val store: AccountProfileStore = getStore()

    init {
        store.labels.onEach {
            when(it) {
                AccountProfileStore.Label.OnNotAuthenticated -> onNotAuthenticated()
            }
        }.flowOn(Dispatchers.Main).launchIn(scope)
    }

    private val state = store.values(this)

    override val isLoading: Value<Boolean> = state.map { it.isLoading }

    override val account: Value<ValueContainer<AccountProfileFull?>> =
        state.map { ValueContainer(it.account) }


}