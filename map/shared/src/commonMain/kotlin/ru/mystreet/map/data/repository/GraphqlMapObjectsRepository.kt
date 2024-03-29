package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.DefaultUpload
import ru.mystreet.core.graphql.type.AddFavouriteMapObjectInput
import ru.mystreet.core.graphql.type.AddMapObjectInput
import ru.mystreet.core.graphql.type.MapObjectEditInput
import ru.mystreet.core.graphql.type.PointInput
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.model.AddMapObjectFavouriteMutation
import ru.mystreet.map.data.model.AddMapObjectImagesMutation
import ru.mystreet.map.data.model.AddMapObjectMutation
import ru.mystreet.map.data.model.DeleteMapObjectMutation
import ru.mystreet.map.data.model.EditMapObjectMutation
import ru.mystreet.map.data.model.GetMapObjectQuery
import ru.mystreet.map.data.model.RemoveMapObjectFavouriteMutation
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

class GraphqlMapObjectsRepository(
    private val apolloClient: ApolloClient,
    private val converter: GraphqlMapObjectsConverter,
) : MapObjectsRepository {

    override suspend fun getMapObjectById(id: Long): MapObject {
        val response = apolloClient.query(GetMapObjectQuery(id.toString())).execute()
        val mapObject =
            response.data?.mapObjects?.info?.mapObjectFull ?: TODO(response.exception.toString())
        return with(converter) { mapObject.convert() }
    }

    override suspend fun setMapObjectFavourite(id: Long, isFavourite: Boolean) {
        if (isFavourite) {
            apolloClient.mutation(
                AddMapObjectFavouriteMutation(
                    AddFavouriteMapObjectInput(id.toString())
                )
            ).execute()
        } else {
            apolloClient.mutation(
                RemoveMapObjectFavouriteMutation(id.toString())
            ).execute()
        }
    }

    override suspend fun addMapObject(
        title: String,
        description: String,
        category: MapObjectCategory,
        latitude: Latitude,
        longitude: Longitude,
        tags: List<String>,
    ): MapObject {
        val response = apolloClient.mutation(
            AddMapObjectMutation(
                AddMapObjectInput(
                    title = title,
                    description = description,
                    category = category.id,
                    point = PointInput(
                        latitude = latitude.value,
                        longitude = longitude.value,
                    ),
                    tags = tags
                )
            )
        ).execute()
        if (response.exception != null)
            TODO()
        return with(converter) { response.data?.mapObjects?.add?.mapObjectFull?.convert() } ?: TODO()
    }

    override suspend fun deleteMapObject(
        id: Long
    ) {
        apolloClient.mutation(
            DeleteMapObjectMutation(id.toString())
        ).execute()
    }

    override suspend fun editMapObject(
        id: Long,
        title: String,
        description: String,
        category: MapObjectCategory,
        tags: List<String>
    ) {
        apolloClient.mutation(
            EditMapObjectMutation(
                id.toString(),
                MapObjectEditInput(
                    category = category.id,
                    title = title,
                    description = description,
                    tags = tags
                )
            )
        ).execute()
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