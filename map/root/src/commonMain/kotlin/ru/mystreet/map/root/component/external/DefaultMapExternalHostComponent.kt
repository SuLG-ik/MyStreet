package ru.mystreet.map.root.component.external

import ru.mystreet.account.component.DefaultAccountHostComponent
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.Map
import ru.mystreet.map.mapobject.component.info.DefaultMapObjectInfoHostComponent

class DefaultMapExternalHostComponent(
    componentContext: DIComponentContext,
    map: Map,
) : AppComponentContext(componentContext), MapExternalHostComponent {

    override val accountHost = DefaultAccountHostComponent(diChildContext("account"))

    override val mapObject =
        DefaultMapObjectInfoHostComponent(diChildContext("map_object"), map = map)

}