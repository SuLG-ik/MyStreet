package ru.mystreet.map.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import ru.mystreet.core.graphql.type.ReviewInput
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.model.AddMapObjectReviewMutation
import ru.mystreet.map.data.model.GetMapObjectReviewsQuery
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.Pageable
import ru.mystreet.map.domain.entity.toInput
import ru.mystreet.map.domain.repository.RemoteMapObjectReviewsRepository

class GraphQLRemoteMapObjectReviewsRepository(
    private val client: ApolloClient,
    private val converter: GraphqlMapObjectsConverter,
) : RemoteMapObjectReviewsRepository {
    override suspend fun getReviews(mapObjectId: Long, pageable: Pageable): List<MapObjectReview> {
        val response = client.query(
            GetMapObjectReviewsQuery(
                id = mapObjectId.toString(),
                pageable = pageable.toInput(),
            )
        ).execute()
        return with(converter) {
            response.data?.mapObjects?.info?.score?.reviews?.reviews?.map { it.mapObjectReviewFull.convert() }
                ?: TODO()
        }
    }

    override suspend fun addReview(
        mapObjectId: Long,
        rating: Int,
        title: String,
        content: String,
        isAuthorHidden: Boolean,
    ) {
        val response = client.mutation(
            AddMapObjectReviewMutation(
                id = mapObjectId.toString(),
                input = ReviewInput(
                    rating = rating,
                    title = Optional.present(title),
                    text = Optional.present(content),
                    isAuthorHidden = isAuthorHidden,
                )
            )
        ).execute()
        if (response.data?.mapObjects?.edit?.rating?.reviews?.add?.id == null)
            TODO()
    }
}