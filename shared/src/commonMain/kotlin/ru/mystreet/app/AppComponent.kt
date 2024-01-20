package ru.mystreet.app

import ru.mystreet.app.component.MyStreetAppComponent
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.context.RootComponent

fun appComponent(componentContext: DIComponentContext): RootComponent {
    return MyStreetAppComponent(componentContext)
}
