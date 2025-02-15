package ru.mystreet.app.feature.dialogs

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.app.feature.dialogs.presentation.DialogConfirmStore
import ru.mystreet.app.feature.dialogs.presentation.DialogConfirmStoreImpl
import ru.mystreet.app.feature.dialogs.presentation.DialogCustomStore
import ru.mystreet.app.feature.dialogs.presentation.DialogCustomStoreImpl

val featureDialogsModule = module {
    factoryOf(::DialogConfirmStoreImpl) bind DialogConfirmStore::class
    factoryOf(::DialogCustomStoreImpl) bind DialogCustomStore::class
}
