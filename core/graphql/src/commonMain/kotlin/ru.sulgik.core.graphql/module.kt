package ru.sulgik.core.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.adapter.KotlinxLocalDateTimeAdapter
import com.apollographql.apollo3.api.CustomScalarType
import com.apollographql.apollo3.exception.ApolloNetworkException
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.core.graphql.BuildKonfig
import ru.mystreet.errors.domain.ErrorDispatcher
import kotlin.time.Duration.Companion.milliseconds

val graphqlModule = module {
    singleOf(::provideApolloClient)
    singleOf(::BasicAuthProvider) bind AuthProvider::class
    singleOf(::AuthProviderAuthRequestConsumer) bind AuthRequestConsumer::class
    singleOf(::AuthHttpInterceptor)
    singleOf(::retryInterceptor)
}

fun provideApolloClient(
    interceptor: AuthHttpInterceptor,
    retryInterceptor: ApolloRetryInterceptor,
): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildKonfig.MYSTREET_GRAPHQL_URL)
        .addHttpInterceptor(interceptor)
        .addInterceptor(retryInterceptor)
        .addCustomScalarAdapter(
            CustomScalarType("DateTime", "kotlinx.datetime.LocalDateTime"),
            KotlinxLocalDateTimeAdapter
        )
        .build()
}

fun retryInterceptor(errorDispatcher: ErrorDispatcher): ApolloRetryInterceptor {
    return ApolloRetryInterceptor(
        retryDuration = exponentialDuration(300.milliseconds),
        maxAttempts = 3,
        shouldRetry = { it is ApolloNetworkException },
        errorDispatcher = errorDispatcher,
    )
}