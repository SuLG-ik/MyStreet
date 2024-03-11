package ru.mystreet.core.auth

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.mystreet.core.auth.domain.usecase.SaveLocalLogin

val authModule = module {
    factoryOf(::SaveLocalLogin)
}