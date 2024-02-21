package ru.sulgik.core.graphql

import com.apollographql.apollo3.ApolloClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.graphql.BuildKonfig

val graphqlModule = module {
    singleOf(::provideApolloClient)
}

fun provideApolloClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildKonfig.MYSTREET_GRAPHQL_URL)
        .build()
}