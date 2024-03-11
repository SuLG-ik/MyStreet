package ru.mystreet.account

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.account.domain.repository.AuthRepository
import ru.mystreet.account.domain.repository.AuthRepositoryImpl
import ru.mystreet.account.domain.usecase.LoginUseCase
import ru.mystreet.account.domain.usecase.RegisterUseCase
import ru.mystreet.account.domain.usecase.RemoteLoginUseCase
import ru.mystreet.account.presentation.AccountLoginStore
import ru.mystreet.account.presentation.AccountLoginStoreImpl

val accountModule = module {
    factoryOf(::AccountLoginStoreImpl) bind AccountLoginStore::class
    factoryOf(::RemoteLoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}