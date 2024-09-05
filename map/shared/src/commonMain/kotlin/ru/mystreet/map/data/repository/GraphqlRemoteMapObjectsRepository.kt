package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import ru.mystreet.map.data.converter.GraphqlFramedMapObjectsConverter
import ru.mystreet.map.data.model.GetFramedMapObjectsPartQuery
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.repository.RemoteMapObjectsRepository

class GraphqlRemoteMapObjectsRepository(
    private val client: ApolloClient,
    private val converter: GraphqlFramedMapObjectsConverter,
) : RemoteMapObjectsRepository {

    override suspend fun getFramedMapObjects(
        frames: MapFrame,
    ): FramedMapObjects {
        val response = client.query(
            GetFramedMapObjectsPartQuery(
                categories = MapObjectCategory.defaultEntries.map { it.id },
                frames = with(converter) { listOf(frames.convert()) }
            )
        ).execute()
        val exception = response.exception
        if (exception != null)
            throw exception
        return with(converter) {
            response.data?.mapObjects?.framed?.map { it.convert() }?.firstOrNull()
        } ?: TODO()
    }

}