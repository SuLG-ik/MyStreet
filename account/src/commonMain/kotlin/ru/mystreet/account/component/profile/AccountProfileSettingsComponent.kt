package ru.mystreet.account.component.profile

import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.account.presentation.profile.AccountProfileSettingStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore

class AccountProfileSettingsComponent(
    componentContext: DIComponentContext,
    private val onNotAuthenticated: () -> Unit,
    private val onBack: () -> Unit,
) : AppComponentContext(componentContext), AccountProfileSettings {

    private val store: AccountProfileSettingStore = getStore()

    init {
        val disposable = store.labels(object : Observer<AccountProfileSettingStore.Label> {
            override fun onComplete() {

            }

            override fun onNext(value: AccountProfileSettingStore.Label) {
                when (value) {
                    AccountProfileSettingStore.Label.OnLogOut -> onNotAuthenticated()
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    override fun onLogOut() {
        store.accept(AccountProfileSettingStore.Intent.LogOut)
    }

    override fun onBack() {
        onBack.invoke()
    }

}