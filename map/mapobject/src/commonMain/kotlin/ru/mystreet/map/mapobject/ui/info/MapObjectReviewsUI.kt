package ru.mystreet.map.mapobject.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.Month.APRIL
import kotlinx.datetime.Month.AUGUST
import kotlinx.datetime.Month.DECEMBER
import kotlinx.datetime.Month.FEBRUARY
import kotlinx.datetime.Month.JANUARY
import kotlinx.datetime.Month.JULY
import kotlinx.datetime.Month.JUNE
import kotlinx.datetime.Month.MARCH
import kotlinx.datetime.Month.MAY
import kotlinx.datetime.Month.NOVEMBER
import kotlinx.datetime.Month.OCTOBER
import kotlinx.datetime.Month.SEPTEMBER
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import kotlinx.datetime.toLocalDateTime
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectReviewsInfo
import ru.mystreet.map.domain.entity.ReviewAuthor
import ru.mystreet.map.mapobject.component.info.MapObjectReviews
import ru.mystreet.uikit.RatingStars
import ru.mystreet.uikit.UIKitCard
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Add
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStarOutline
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectReviewsUI(
    component: MapObjectReviews,
    modifier: Modifier = Modifier,
) {
    val paging = component.pagingData.collectAsLazyPagingItems()
    SideEffect {
        component.onRefresh = paging::refresh
    }
    val info by component.info.subscribeAsState()
    MapObjectReviewsScreen(
        info = info,
        data = paging,
        onAddReview = component::onAddReview,
        modifier = modifier,
    )
}

@Composable
fun MapObjectReviewsList(
    data: LazyPagingItems<MapObjectReview>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            data.itemCount,
            contentType = { "review" },
            key = { data[it]?.id ?: (it - 10000) },
        ) {
            val item = data[it] ?: return@items
            MapObjectReview(
                mapObjectReview = item,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun MapObjectReview(
    mapObjectReview: MapObjectReview,
    modifier: Modifier = Modifier,
) {
    UIKitCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Author(mapObjectReview.author)
            Spacer(modifier = Modifier.height((2.5).dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RatingStars(
                    mapObjectReview.rating,
                )
                CreatedDate(
                    mapObjectReview,
                )
            }
            val title = mapObjectReview.title
            if (!title.isNullOrEmpty())
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge
                )
            val text = mapObjectReview.text
            if (!text.isNullOrEmpty())
                Text(text)
        }
    }
}

@Composable
fun CreatedDate(
    review: MapObjectReview,
    modifier: Modifier = Modifier,
) {
    Text(
        review.createdDate.formatDate(),
        color = LocalContentColor.current.copy(alpha = 0.6f),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
    )
}

val currentClock = Clock.System

fun LocalDateTime.formatDate(): String {
    val currentDate = currentClock.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val period = currentDate.periodUntil(date)
    return when (period.days) {
        0 -> "Сегодня"
        -1 -> "Вчера"
        else -> "$dayOfMonth ${formatMonth(month)} $year"
    }
}

fun formatMonth(month: Month): String {
    return when (month) {
        JANUARY -> "января"
        FEBRUARY -> "февраля"
        MARCH -> "марта"
        APRIL -> "апреля"
        MAY -> "мая"
        JUNE -> "июня"
        JULY -> "июля"
        AUGUST -> "августа"
        SEPTEMBER -> "сентября"
        OCTOBER -> "октября"
        NOVEMBER -> "ноября"
        DECEMBER -> "декабоя"
        else -> throw IllegalArgumentException("Illegal month $month")
    }
}

@Composable
fun Author(
    author: ReviewAuthor?,
    modifier: Modifier = Modifier,
) {
    val authorName = author?.name ?: "Неизвестный автор"
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AuthorImage(
            authorName,
            modifier = Modifier.size(35.dp),
        )
        Text(
            authorName,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
        )
    }
}

@Composable
fun AuthorImage(
    name: String,
    modifier: Modifier,
) {
    Box(
        modifier = modifier.size(35.dp).clip(CircleShape)
            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center,
    ) {
        AuthorLetters(
            name = name,
        )
    }
}

@Composable
fun AuthorLetters(
    name: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: Modifier = Modifier,
) {
    Text(
        text = firstLetters(name = name),
        textAlign = TextAlign.Center,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 18.sp,
        style = style.copy(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both
            )
        ),
        modifier = modifier,
    )
}

fun firstLetters(name: String): String {
    return buildString {
        if (name.isBlank()) {
            append("НА")
            return@buildString
        }
        append(name.splitToSequence(" ").take(2).map { it.first() }.joinToString(""))
    }
}

@Composable
fun MapObjectReviewsScreen(
    info: MapObjectReviewsInfo,
    data: LazyPagingItems<MapObjectReview>,
    onAddReview: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ReviewsHeader(
            info = info,
            onAddReview = onAddReview,
            modifier = Modifier.fillMaxWidth()
        )
        MapObjectReviewsList(
            data = data,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ReviewsHeader(
    info: MapObjectReviewsInfo,
    onAddReview: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(40.dp),
    ) {
        Text(
            text = reviewsTitle(info),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        if (info.isAddReviewAvailable) {
            IconButton(onClick = onAddReview) {
                Icon(
                    imageVector = UIKitIconPack.Add,
                    contentDescription = null,
                    modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
                )
            }
        }
    }
}

private fun reviewsTitle(info: MapObjectReviewsInfo): String {
    if (info.reviewsCount == 0)
        return "Ваш отзыв будет первым"
    return "Отзывы (${info.reviewsCount})"
}