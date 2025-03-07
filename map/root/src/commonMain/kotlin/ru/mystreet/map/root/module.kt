package ru.mystreet.map.root

import org.koin.core.annotation.Module
import org.koin.dsl.binds
import org.koin.dsl.module
import ru.mystreet.account.accountModule
import ru.mystreet.errors.data.DefaultErrorDispatcher
import ru.mystreet.errors.domain.ErrorDispatcher
import ru.mystreet.errors.domain.MutableErrorDispatcher
import ru.mystreet.errors.errorsModule
import ru.mystreet.map.general.mapGeneralModule
import ru.mystreet.map.map.mapModule
import ru.mystreet.map.mapSharedModule
import ru.mystreet.map.mapobject.mapObjectModule
import ru.sulgik.core.crashlytics.CrashlyticsErrorPostProcessor

val mapHostModule = module {
    includes(
        mapGeneralModule,
        mapSharedModule,
        mapModule,
        accountModule,
        mapObjectModule,
        errorsModule,
    )
    single {
        DefaultErrorDispatcher(
            errorPostProcessors = arrayOf(CrashlyticsErrorPostProcessor()),
        )
    } binds arrayOf(
        ErrorDispatcher::class,
        MutableErrorDispatcher::class
    )
}