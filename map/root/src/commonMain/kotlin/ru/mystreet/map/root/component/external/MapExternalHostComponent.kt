package ru.mystreet.map.root.component.external

import ru.mystreet.account.component.DefaultAccountHostComponent
import ru.mystreet.map.mapobject.component.info.DefaultMapObjectInfoHostComponent

interface MapExternalHostComponent {
    val accountHost: DefaultAccountHostComponent
    val mapObject: DefaultMapObjectInfoHostComponent
}