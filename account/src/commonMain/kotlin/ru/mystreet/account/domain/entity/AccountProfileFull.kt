package ru.mystreet.account.domain.entity

data class AccountProfileFull(
    val username: String,
    val email: String,
    val name: String,
    val extra: AccountProfileExtra,
)

data class ContinuableList<T>(
    val isContinueAvailable: Boolean,
    val realSize: Long,
    val content: List<T>,
)

data class ReviewShortInfo(
    val id: Long,
    val title: String,
    val rating: Int,
    val image: String?,
)

data class FavouriteShortInfo(
    val id: Long,
    val title: String,
    val image: String?,
)

data class AccountProfileExtra(
    val reviews: ContinuableList<ReviewShortInfo>,
    val favourite: ContinuableList<FavouriteShortInfo>,
)
