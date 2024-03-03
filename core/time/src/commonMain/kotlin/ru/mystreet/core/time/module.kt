package ru.mystreet.core.time

import kotlinx.datetime.Clock
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dateTimeModule: Module = module {
    singleOf(::createSystemClock)
    singleOf(::DateTimeServiceImpl) bind DateTimeService::class
}

private fun createSystemClock(): Clock {
    return Clock.System
}