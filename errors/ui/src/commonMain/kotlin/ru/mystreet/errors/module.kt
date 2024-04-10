package ru.mystreet.errors

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import ru.mystreet.errors.data.DefaultErrorDispatcher
import ru.mystreet.errors.domain.ErrorDispatcher
import ru.mystreet.errors.domain.MutableErrorDispatcher
import ru.mystreet.errors.store.ErrorsListStore
import ru.mystreet.errors.store.ErrorsListStoreImpl

val errorsModule = module {
    factoryOf(::ErrorsListStoreImpl) bind ErrorsListStore::class
    single{DefaultErrorDispatcher() } binds arrayOf(
        ErrorDispatcher::class,
        MutableErrorDispatcher::class
    )
}