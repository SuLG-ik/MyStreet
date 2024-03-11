package ru.sulgik.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.ktor.BuildKonfig

val ktorModule = module {
    singleOf(::provideKtorHttpClient)
}

fun provideKtorHttpClient(): HttpClient {
    return HttpClient(CIO) {
        defaultRequest {
            url(BuildKonfig.MYSTREET_API_URL)
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}