package ru.sulgik.core.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.core.graphql.BuildKonfig

val graphqlModule = module {
    singleOf(::provideApolloClient)
    singleOf(::BasicAuthProvider) bind AuthProvider::class
    singleOf(::AuthProviderAuthRequestConsumer) bind AuthRequestConsumer::class
    singleOf(::AuthHttpInterceptor)
}

fun provideApolloClient(interceptor: AuthHttpInterceptor): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildKonfig.MYSTREET_GRAPHQL_URL)
        .addHttpInterceptor(interceptor)
        .build()
}