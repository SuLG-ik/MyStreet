package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.DefaultUpload
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.model.AddMapObjectImagesMutation
import ru.mystreet.map.data.model.AddMapObjectMutation
import ru.mystreet.map.data.model.GetAllMapObjectsQuery
import ru.mystreet.map.data.model.GetMapObjectQuery
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
        val mapObjects = response.data?.getMapObjects ?: TODO(response.exception.toString())
        return converter.convert(mapObjects)
    }

    override suspend fun getMapObjectById(id: Long): MapObject {
        val response = apolloClient.query(GetMapObjectQuery(id.toString())).execute()
        val mapObject =
            response.data?.getMapObject?.mapObjectFull ?: TODO(response.exception.toString())
        return converter.convert(mapObject)
    }

    override suspend fun addMapObject(
        title: String,
        description: String,
        category: MapObjectCategory,
        latitude: Latitude,
        longitude: Longitude,
        tags: List<String>,
    ) {
        val response = apolloClient.mutation(
            AddMapObjectMutation(
                title = title,
                description = description,
                category = category.id,
                latitude = latitude.value,
                longitude = longitude.value,
                tags = tags
            )
        ).execute()
        if (response.exception != null)
            TODO()
    }

    override suspend fun uploadMapObjectImages(
        mapObjectId: Long,
        images: List<ByteArray>,
    ) {
        images.forEach {
            val upload = DefaultUpload.Builder()
                .contentType("image/jpeg")
                .content(it)
                .fileName("image")
                .build()
            apolloClient.mutation(AddMapObjectImagesMutation(mapObjectId.toString(), upload))
                .execute()
        }
    }

}