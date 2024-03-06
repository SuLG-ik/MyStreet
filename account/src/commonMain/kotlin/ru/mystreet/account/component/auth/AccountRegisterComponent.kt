package ru.mystreet.account.component.auth

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext

class AccountRegisterComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
) : AppComponentContext(componentContext), AccountRegister {

    override fun onBack() {
        onBack.invoke()
    }


}