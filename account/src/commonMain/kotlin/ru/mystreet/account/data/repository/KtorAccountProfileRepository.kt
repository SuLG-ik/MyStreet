package ru.mystreet.account.data.repository

import com.apollographql.apollo3.ApolloClient
import ru.mystreet.account.data.converter.GraphQLAccountProfileConverter
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.repository.AccountProfileRepository
import ru.mystreet.map.account.GetAccountInfoQuery

class KtorAccountProfileRepository(
    private val client: ApolloClient,
    private val converter: GraphQLAccountProfileConverter,
) : AccountProfileRepository {
    override suspend fun info(): AccountProfileFull {
        val response = client.query(GetAccountInfoQuery())
            .execute()

        val result = response.data?.users?.info?.accountProfileFull ?: throw Exception()
        return with(converter) { result.convert() }
    }
}