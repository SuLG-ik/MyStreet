package ru.mystreet.account

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan
class AccountModule

val accountModule = AccountModule().module