package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.model.AddMapObjectMutation
import ru.mystreet.map.data.model.GetAllMapObjectsQuery
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

class GraphqlMapObjectsRepository(
    private val apolloClient: ApolloClient,
    private val converter: GraphqlMapObjectsConverter,
) : MapObjectsRepository {

    override suspend fun getAllMapObjects(): List<MapObject> {
        val response = apolloClient.query(GetAllMapObjectsQuery()).execute()
        response.exception?.printStackTrace()
        val mapObjects = response.data?.getMapObjects ?: TODO(response.exception.toString())
        return converter.convert(mapObjects)
    }

    override suspend fun addMapObject(
        title: String,
        description: String,
        category: MapObjectCategory,
        latitude: Latitude,
        longitude: Longitude,
    ) {
        val response = apolloClient.mutation(
            AddMapObjectMutation(
                title = title,
                description = description,
                category = category.id,
                latitude = latitude.value,
                longitude = longitude.value,
                tags = emptyList()
            )
        ).execute()
        if (response.exception != null)
            TODO()
    }

}