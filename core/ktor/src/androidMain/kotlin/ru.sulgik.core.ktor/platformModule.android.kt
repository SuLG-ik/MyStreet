package ru.sulgik.core.ktor

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.bind
import org.koin.dsl.module

actual val ktorPlatformModule = module {
    single { OkHttp } bind HttpClientEngineFactory::class
}