package ru.mystreet.map.mapobject.ui.info

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.ui.Chip
import ru.mystreet.map.ui.info.MapObjectInfoPlaceholder
import ru.mystreet.uikit.UIKitAsyncImage
import ru.mystreet.uikit.UIKitBottomBar
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AddOutlined
import ru.mystreet.uikit.iconpack.uikiticonpack.EditIcon
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStarOutline
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectInfoScreen(
    reviews: @Composable () -> Unit,
    isLoading: Boolean,
    mapObject: MapObject?,
    onImagePicker: () -> Unit,
    onFavourite: (Boolean) -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Crossfade(isLoading, modifier = modifier) {
        if (it) {
            MapObjectInfoPlaceholder(modifier = Modifier.fillMaxWidth())
        } else {
            if (mapObject != null) {
                MapObjectInfo(
                    reviews = reviews,
                    mapObject = mapObject,
                    onImagePicker = onImagePicker,
                    onFavourite = onFavourite,
                    onEdit = onEdit,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapObjectInfo(
    reviews: @Composable () -> Unit,
    mapObject: MapObject,
    onImagePicker: () -> Unit,
    onFavourite: (Boolean) -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = UIKitSizeTokens.BottomBarHeight),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Images(mapObject.images, onImagePicker, modifier = Modifier.fillMaxWidth())
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Title(
                        mapObject = mapObject,
                        modifier = Modifier.fillMaxWidth().weight(1f),
                    )
                    Rating(mapObject = mapObject)
                }
                Description(
                    mapObject = mapObject,
                    modifier = Modifier.fillMaxWidth(),
                )
                Tags(
                    mapObject = mapObject,
                    modifier = Modifier.fillMaxWidth(),
                )
                reviews()
            }
        }
        UIKitBottomBar(
            actions = {
                EditButton(onEdit)
                Favourite(
                    mapObject = mapObject,
                    onToggle = onFavourite,
                )
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
private fun EditButton(onEdit: () -> Unit) {
    IconButton(onClick = onEdit) {
        Icon(
            UIKitIconPack.EditIcon,
            contentDescription = null,
            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
        )
    }
}

@Composable
fun Images(
    images: List<String>,
    onImagePicker: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            count = images.size,
            key = { images[it] },
            contentType = { "image" }
        ) {
            Image(
                images[it],
                modifier = Modifier.imageSize(this, it, images.size).height(Height)
            )
        }
        item {
            AddPhoto(
                onClick = onImagePicker,
                modifier = Modifier.addImageSize(this, images.size).height(Height)
            )
        }
    }
}

@Composable
private fun Modifier.imageSize(scope: LazyItemScope, currentIndex: Int, size: Int): Modifier {
    if (size < 1)
        return this
    if (size == 1) {
        return this then with(scope) { fillParentMaxWidth(0.8f) }
    }
    return this then with(scope) { fillParentMaxWidth(0.33f) }
}

@Composable
private fun Modifier.addImageSize(scope: LazyItemScope, size: Int): Modifier {
    if (size < 1)
        return this then with(scope) { fillParentMaxWidth() }
    return this
}

val Height = 120.dp

@Composable
fun Image(
    image: String,
    modifier: Modifier = Modifier,
) {
    UIKitAsyncImage(
        image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(MaterialTheme.shapes.medium),
    )
}

@Composable
fun Description(mapObject: MapObject, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        if (mapObject.description.isEmpty()) {
            Text("Нет описания", modifier = modifier)
        } else {
            Text(mapObject.description, modifier = modifier)
        }
    }
}

@Composable
fun Title(
    mapObject: MapObject,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = mapObject.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "Категория: ${mapObject.category.format()}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun MapObjectCategory.format(): String {
    return when (this) {
        MapObjectCategory.Bench -> "лавочка"
        MapObjectCategory.Playground -> "площадка"
        MapObjectCategory.StreetLight -> "освещение"
        MapObjectCategory.Monument -> "памятник"
        MapObjectCategory.Fountain -> "фонтан"
        MapObjectCategory.Bower -> "беседка"
        MapObjectCategory.GreenArea -> "зелёная зона"
        MapObjectCategory.PublicWC -> "общественный туалет"
        MapObjectCategory.Trash -> "урна для мусора"
        MapObjectCategory.Installation -> "инсталяция"
        MapObjectCategory.Other -> "другое"
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    mapObject: MapObject,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Теги",
            style = MaterialTheme.typography.titleMedium,
        )
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            mapObject.tags.forEach {
                Chip(it.title)
            }
        }
    }
}

@Composable
fun Favourite(
    mapObject: MapObject,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val forUser = mapObject.forUser
    if (forUser != null) {
        var isFavourite by rememberSaveable(forUser) { mutableStateOf(forUser.isFavourite) }
        Crossfade(isFavourite, animationSpec = tween(225)) {
            when (it) {
                true ->
                    Image(
                        UIKitIconPack.RatingStar,
                        contentDescription = null,
                        modifier = modifier.size(UIKitSizeTokens.DefaultIconSize).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            onToggle(false)
                            isFavourite = false
                        },
                    )

                false ->
                    Icon(
                        UIKitIconPack.RatingStarOutline,
                        contentDescription = null,
                        modifier = modifier.size(UIKitSizeTokens.DefaultIconSize).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            onToggle(true)
                            isFavourite = true
                        },
                        tint = MaterialTheme.colorScheme.outline
                    )
            }
        }
    }
}

@Composable
fun Rating(
    mapObject: MapObject,
    modifier: Modifier = Modifier,
) {
    val rating = mapObject.rating
    if (rating != null) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = modifier
        ) {
            Image(
                UIKitIconPack.RatingStar,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.SmallIconSize),
            )
            Text(rating)
        }
    }
}


@Composable
fun AddPhoto(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
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
                "Добавить фото",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}