package ru.sulgik.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.ktor.BuildKonfig

val ktorModule = module {
    includes(ktorPlatformModule)
    singleOf(::provideKtorHttpClient)
}

fun provideKtorHttpClient(engine: HttpClientEngineFactory<*>): HttpClient {
    return HttpClient(engine) {
        defaultRequest {
            url(BuildKonfig.MYSTREET_API_URL)
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}