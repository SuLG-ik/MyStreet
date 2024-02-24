package ru.mystreet.map.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Add
import ru.mystreet.uikit.iconpack.uikiticonpack.AddOutlined
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectInfoScreen(
    isLoading: Boolean,
    mapObject: MapObject?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (isLoading) {
            Text("Загрузка")
        } else {
            if (mapObject != null) {
                MapObjectInfo(
                    mapObject = mapObject,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp).padding(bottom = 15.dp),
                )
            }
        }
    }
}

@Composable
fun MapObjectInfo(
    mapObject: MapObject,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        AddPhoto(
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Title(
                mapObject = mapObject,
                modifier = Modifier.fillMaxWidth().weight(1f),
            )
            Rating()
        }
        Description(
            mapObject = mapObject,
            modifier = Modifier.fillMaxWidth(),
        )
        Tags(
            mapObject = mapObject,
            modifier = Modifier.fillMaxWidth(),
        )
        Reviews(
            mapObject = mapObject,
            modifier = modifier,
        )
    }
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
fun Reviews(
    mapObject: MapObject,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Отзывы (3)",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
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

@Composable
fun Rating(modifier: Modifier = Modifier) {
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
        Text("4.5")
    }
}


@Composable
fun AddPhoto(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier.height(80.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
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