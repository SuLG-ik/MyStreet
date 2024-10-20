package ru.sulgik.core.graphql

import com.apollographql.apollo.api.http.HttpRequest

interface AuthRequestConsumer {

    suspend fun consume(request: HttpRequest): HttpRequest

}