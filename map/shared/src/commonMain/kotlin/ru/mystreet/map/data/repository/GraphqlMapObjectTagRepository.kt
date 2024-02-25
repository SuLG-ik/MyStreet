package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import ru.mystreet.map.data.converter.GraphqlMapObjectTagsConverter
import ru.mystreet.map.data.model.GetMapObjectTagsQuery
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.domain.repository.MapObjectTagRepository

class GraphqlMapObjectTagRepository(
    private val client: ApolloClient,
    private val converter: GraphqlMapObjectTagsConverter,
) : MapObjectTagRepository {
    override suspend fun getMapObjectsTag(
        category: MapObjectCategory,
        query: String,
    ): List<MapObjectTag> {
        val response =
            client.query(GetMapObjectTagsQuery(category = category.id, Optional.present(query)))
                .execute()
        return converter.convert(response.data?.getMapObjectTags ?: TODO())
    }
}