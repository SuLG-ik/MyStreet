package ru.sulgik.core.ktor

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.bind
import org.koin.dsl.module


actual val ktorPlatformModule = module {
    single { Darwin } bind HttpClientEngineFactory::class
}