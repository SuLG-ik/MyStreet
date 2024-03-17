package ru.sulgik.core.graphql

import com.apollographql.apollo3.api.http.HttpRequest

interface AuthRequestConsumer {

    suspend fun consume(request: HttpRequest): HttpRequest

}