package ru.mystreet.app

import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.root.component.AppRoot
import ru.mystreet.root.component.AppRootComponent

fun appComponent(componentContext: DIComponentContext): AppRoot {
    return AppRootComponent(componentContext)
}
