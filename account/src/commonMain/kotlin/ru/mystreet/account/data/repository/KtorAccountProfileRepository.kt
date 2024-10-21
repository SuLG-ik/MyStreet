package ru.mystreet.account.data.repository

import com.apollographql.apollo.ApolloClient
import org.koin.core.annotation.Factory
import ru.mystreet.account.data.converter.GraphQLAccountProfileConverter
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.repository.AccountProfileRepository
import ru.mystreet.core.graphql.type.PageableInput
import ru.mystreet.map.account.GetAccountInfoQuery

@Factory
class KtorAccountProfileRepository(
    private val client: ApolloClient,
    private val converter: GraphQLAccountProfileConverter,
) : AccountProfileRepository {
    override suspend fun info(): AccountProfileFull {
        val response = client.query(GetAccountInfoQuery(pageable = PageableInput(
            page = 0,
            pageSize = 10
        )
        ))
            .execute()
        val result = response.data?.users?.info ?: throw Exception()
        return with(converter) { result.convert() }
    }
}