package ru.mystreet.map.root

import org.koin.dsl.module
import ru.mystreet.account.accountModule
import ru.mystreet.map.general.mapGeneralModule
import ru.mystreet.map.map.mapModule
import ru.mystreet.map.mapSharedModule
import ru.mystreet.map.mapobject.mapObjectModule

val mapHostModule = module {
    includes(
        mapGeneralModule,
        mapSharedModule,
        mapModule,
        accountModule,
        mapObjectModule,
    )
}