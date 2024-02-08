package ru.mystreet.app.store

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val mviModule = module {
    single { DefaultStoreFactory() } bind StoreFactory::class
    single { Dispatchers.IO } bind CoroutineDispatcher::class
}