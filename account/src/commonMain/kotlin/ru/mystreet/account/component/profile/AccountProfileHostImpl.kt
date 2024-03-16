package ru.mystreet.account.component.profile

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.presentation.profile.AccountProfileStore
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
        val disposable = store.labels(object : Observer<AccountProfileStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: AccountProfileStore.Label) {
                when(value) {
                    AccountProfileStore.Label.OnNotAuthenticated -> onNotAuthenticated()
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    private val state = store.values(this)

    override val isLoading: Value<Boolean> = state.map { it.isLoading }

    override val account: Value<ValueContainer<AccountProfileFull?>> =
        state.map { ValueContainer(it.account) }


}