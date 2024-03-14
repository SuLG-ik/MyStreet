package ru.mystreet.map.map.data.repository

import com.apollographql.apollo3.ApolloClient
import ru.mystreet.map.data.model.GetFramedMapObjectsPartQuery
import ru.mystreet.map.map.data.converter.GraphqlFramedMapObjectsConverter
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.repository.RemoteMapObjectsRepository

class GraphqlRemoteMapObjectsRepository(
    private val client: ApolloClient,
    private val converter: GraphqlFramedMapObjectsConverter,
) : RemoteMapObjectsRepository {

    override suspend fun getFramedMapObjects(
        frames: MapFrame,
    ): FramedMapObjects {
        val response = client.query(
            GetFramedMapObjectsPartQuery(
                frames = with(converter) { listOf(frames.convert()) }
            )
        ).execute()
        if (response.exception != null) TODO()
        return with(converter) {
            response.data?.mapObjects?.framed?.map { it.convert() }?.firstOrNull()
        } ?: TODO()
    }

}