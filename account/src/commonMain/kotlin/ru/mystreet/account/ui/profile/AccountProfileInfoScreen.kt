package ru.mystreet.account.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.entity.ContinuableList
import ru.mystreet.account.domain.entity.FavouriteShortInfo
import ru.mystreet.account.domain.entity.ReviewShortInfo
import ru.mystreet.uikit.RatingStars
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AddOutlined
import ru.mystreet.uikit.iconpack.uikiticonpack.ArrowRight
import ru.mystreet.uikit.iconpack.uikiticonpack.Settings
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun AccountProfileInfoScreen(
    account: AccountProfileFull,
    onSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            AccountProfileTopBar(
                modifier = Modifier.fillMaxWidth(),
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = onSettings) {
                        Icon(
                            UIKitIconPack.Settings,
                            contentDescription = null,
                            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            AccountProfileInfo(
                account = account,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            )
            AccountProfileExtra(
                account = account,
                onMoreReviews = {},
                onMoreFavourite = {},
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountProfileTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text("Профиль")
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountProfileInfo(account: AccountProfileFull, modifier: Modifier = Modifier) {
    OutlinedCard(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AccountProfilePicture(account = account, modifier = Modifier.size(120.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = account.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = account.username,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = account.email,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun AccountProfilePicture(
    account: AccountProfileFull,
    modifier: Modifier = Modifier,
) {
    AccountProfileAddPicture(
        onClick = {},
        modifier = modifier,
    )
}

@Composable
fun AccountProfileAddPicture(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick,
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)
        ) {
            Icon(
                UIKitIconPack.AddOutlined,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
            )
            Text(
                "Добавить",
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun AccountProfileExtra(
    account: AccountProfileFull,
    onMoreFavourite: () -> Unit,
    onMoreReviews: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FavouriteBlock(
            favourite = account.extra.favourite,
            onMore = onMoreFavourite,
            modifier = Modifier.fillMaxWidth()
        )
        ReviewsBlock(
            reviews = account.extra.reviews,
            onMore = onMoreReviews,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FavouriteBlock(
    favourite: ContinuableList<FavouriteShortInfo>,
    onMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CountableBlock(
        title = "Избранное",
        list = favourite,
        item = {
            FavouriteItem(it, modifier = Modifier.size(width = 150.dp, height = 200.dp))
        },
        key = { it.id },
        onMore = onMore,
        modifier = modifier,
    )
}

@Composable
fun ReviewsBlock(
    reviews: ContinuableList<ReviewShortInfo>,
    onMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CountableBlock(
        title = "Отзывы",
        list = reviews,
        item = {
            ReviewItem(it, modifier = Modifier.size(width = 150.dp, height = 200.dp))
        },
        key = { it.id },
        onMore = onMore,
        modifier = modifier,
    )
}

@Composable
fun ReviewItem(review: ReviewShortInfo, modifier: Modifier = Modifier) {
    CountableBlockItem(
        background = {
            NoPicture(
                modifier = Modifier.fillMaxSize()
            )
        },
        label = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            ) {
                Text(
                    text = review.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingStars(review.rating)
            }
        },
        modifier = modifier,
    )
}

@Composable
fun FavouriteItem(review: FavouriteShortInfo, modifier: Modifier = Modifier) {
    CountableBlockItem(
        background = {
            NoPicture(
                modifier = Modifier.fillMaxSize()
            )
        },
        label = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            ) {
                Text(
                    text = review.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
fun NoPicture(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Text("Нет фото")
    }
}

@Composable
fun <T> CountableBlock(
    title: String,
    list: ContinuableList<T>,
    item: @Composable (T) -> Unit,
    key: (T) -> Any,
    onMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TitledBlock(
        title = buildString {
            append(title)
            if (list.realSize > 0) {
                append(" (${list.realSize})")
            }
        },
        modifier = modifier
    ) {
        if (list.realSize > 0) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(list.content, key = key, contentType = { "item" }) {
                    item(it)
                }
                if (list.isContinueAvailable) {
                    item(
                        key = { -1 },
                        contentType = { "more_items" },
                    ) {
                        val interactionSource = remember { MutableInteractionSource() }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                5.dp,
                                Alignment.CenterVertically
                            ),
                            modifier = Modifier.size(width = 250.dp, height = 400.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = onMore,
                                )
                                .then(modifier),
                        ) {
                            Text("И другие")
                            OutlinedButton(
                                onClick = onMore,
                                interactionSource = interactionSource,
                            ) {
                                Text("Перейти")
                                Icon(
                                    UIKitIconPack.ArrowRight,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                )
                            }
                        }
                    }
                }

            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    "Список пока пуст",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun CountableBlockItem(
    background: @Composable() (() -> Unit),
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .then(modifier),
    ) {
        background.invoke()
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            label()
        }
    }
}

@Composable
fun TitledBlock(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        content()
    }
}