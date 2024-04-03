package ru.mystreet.map.mapobject.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.mapobject.component.info.MapObjectReviews
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Add
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectReviewsUI(
    component: MapObjectReviews,
    modifier: Modifier = Modifier
) {
    val paging = component.pagingData.collectAsLazyPagingItems()
    MapObjectReviewsScreen(
        data = paging,
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
    Card(
        modifier = modifier
    ) {
        Text("Author: ${mapObjectReview.author.name}")
        Text(mapObjectReview.title, style = MaterialTheme.typography.titleLarge)
        Text(mapObjectReview.text)
    }
}


@Composable
fun MapObjectReviewsScreen(
    data: LazyPagingItems<MapObjectReview>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ReviewsHeader(
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
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = "Отзывы (3)",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = UIKitIconPack.Add,
                    contentDescription = null,
                    modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
                )
            }
        }
    }
}