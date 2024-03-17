package ru.mystreet.account

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.account.data.converter.AuthConverter
import ru.mystreet.account.data.converter.GraphQLAccountProfileConverter
import ru.mystreet.account.data.repository.KtorAccountProfileRepository
import ru.mystreet.account.data.repository.KtorAuthRepository
import ru.mystreet.account.domain.repository.AccountProfileRepository
import ru.mystreet.account.domain.repository.AuthRepository
import ru.mystreet.account.domain.usecase.LoadAccountProfileFullUseCase
import ru.mystreet.account.domain.usecase.LoginUseCase
import ru.mystreet.account.domain.usecase.RegisterUseCase
import ru.mystreet.account.domain.usecase.RemoteLoginUseCase
import ru.mystreet.account.presentation.AccountLoginStore
import ru.mystreet.account.presentation.AccountLoginStoreImpl
import ru.mystreet.account.presentation.AccountProfileStore
import ru.mystreet.account.presentation.AccountProfileStoreImpl
import ru.mystreet.account.presentation.AccountRegisterStore
import ru.mystreet.account.presentation.AccountRegisterStoreImpl

val accountModule = module {
    factoryOf(::AccountLoginStoreImpl) bind AccountLoginStore::class
    factoryOf(::AccountRegisterStoreImpl) bind AccountRegisterStore::class
    factoryOf(::AccountProfileStoreImpl) bind AccountProfileStore::class
    factoryOf(::RemoteLoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::LoadAccountProfileFullUseCase)
    singleOf(::AuthConverter)
    singleOf(::KtorAccountProfileRepository) bind AccountProfileRepository::class
    singleOf(::GraphQLAccountProfileConverter)
    singleOf(::KtorAuthRepository) bind AuthRepository::class
}