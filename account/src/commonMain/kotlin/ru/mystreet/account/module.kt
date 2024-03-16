package ru.mystreet.account

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.account.data.converter.AuthConverter
import ru.mystreet.account.data.converter.GraphQLAccountProfileConverter
import ru.mystreet.account.data.repository.KtorAccountProfileRepository
import ru.mystreet.account.data.repository.KtorAuthRepository
import ru.mystreet.account.data.service.DirectAuthFieldFormatter
import ru.mystreet.account.domain.repository.AccountProfileRepository
import ru.mystreet.account.domain.repository.AuthRepository
import ru.mystreet.account.domain.service.AuthFieldFormatter
import ru.mystreet.account.domain.service.AuthFieldValidator
import ru.mystreet.account.domain.service.DirectAuthFieldValidator
import ru.mystreet.account.domain.usecase.FormatLoginUseCase
import ru.mystreet.account.domain.usecase.FormatPasswordUseCase
import ru.mystreet.account.domain.usecase.LoadAccountProfileFullUseCase
import ru.mystreet.account.domain.usecase.LoginIsContinueAvailableUseCase
import ru.mystreet.account.domain.usecase.LoginUseCase
import ru.mystreet.account.domain.usecase.ProvideLoginUseCase
import ru.mystreet.account.domain.usecase.ProvidePasswordUseCase
import ru.mystreet.account.domain.usecase.RegisterUseCase
import ru.mystreet.account.domain.usecase.RemoteLoginUseCase
import ru.mystreet.account.domain.usecase.ValidateLoginUseCase
import ru.mystreet.account.domain.usecase.ValidatePasswordUseCase
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
    factoryOf(::FormatPasswordUseCase)
    factoryOf(::FormatLoginUseCase)
    factoryOf(::ValidateLoginUseCase)
    factoryOf(::ValidatePasswordUseCase)
    factoryOf(::ProvideLoginUseCase)
    factoryOf(::ProvidePasswordUseCase)
    factoryOf(::LoginIsContinueAvailableUseCase)
    singleOf(::DirectAuthFieldFormatter) bind AuthFieldFormatter::class
    singleOf(::DirectAuthFieldValidator) bind AuthFieldValidator::class
    singleOf(::AuthConverter)
    singleOf(::KtorAccountProfileRepository) bind AccountProfileRepository::class
    singleOf(::GraphQLAccountProfileConverter)
    singleOf(::KtorAuthRepository) bind AuthRepository::class
}