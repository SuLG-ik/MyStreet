package ru.mystreet.account.data.converter

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.AccountProfileExtra
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.entity.ContinuableList
import ru.mystreet.account.domain.entity.FavouriteShortInfo
import ru.mystreet.account.domain.entity.ReviewShortInfo
import ru.mystreet.map.account.GetAccountInfoQuery
import ru.mystreet.map.account.fragment.AccountFavouriteDetails
import ru.mystreet.map.account.fragment.AccountReviewsDetails

@Factory
class GraphQLAccountProfileConverter {
    private fun AccountFavouriteDetails.convert(): FavouriteShortInfo {
        return FavouriteShortInfo(
            title = mapObject.title,
            id = mapObject.id.toLong(),
            image = null,
        )
    }

    private fun AccountReviewsDetails.convert(): ReviewShortInfo {
        return ReviewShortInfo(
            id = id.toLong(),
            title = mapObject.title,
            rating = rating,
            image = null
        )
    }

    fun GetAccountInfoQuery.Info.convert(): AccountProfileFull {
        return AccountProfileFull(
            username = accountProfileFull.username,
            email = accountProfileFull.email,
            name = accountProfileFull.name,
            extra = AccountProfileExtra(
                reviews = ContinuableList(
                    isContinueAvailable = false,
                    realSize = reviews.count.toLong(),
                    content = reviews.list.map { it.accountReviewsDetails.convert() }),
                favourite = ContinuableList(
                    isContinueAvailable = false,
                    realSize = favourite.count.toLong(),
                    content = favourite.list.map { it.accountFavouriteDetails.convert() }),
            )
        )
    }
}
