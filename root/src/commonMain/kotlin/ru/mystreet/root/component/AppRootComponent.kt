package ru.mystreet.root.component

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.root.component.MapHost
import ru.mystreet.map.root.component.MapHostComponent

class AppRootComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), AppRoot {

    override val mapHost: MapHost = MapHostComponent(diChildContext("map_host"))

}
