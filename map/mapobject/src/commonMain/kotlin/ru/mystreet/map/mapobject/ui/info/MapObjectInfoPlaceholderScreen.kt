package ru.mystreet.map.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.ui.Chip
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.placeholder.placeholder
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectInfoPlaceholder(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = UIKitSizeTokens.BottomBarHeight),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ImagesPlaceholder(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp))
            Column(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TitlePlaceholder(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                    )
                }
                DescriptionPlaceholder(
                    modifier = Modifier.fillMaxWidth(),
                )
                TagsPlaceholder(
                    modifier = Modifier.fillMaxWidth(),
                )
                ReviewsPlaceholder(
                    modifier = modifier,
                )
            }
        }
    }

}

@Composable
fun ImagesPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        repeat(5) {
            ImagePlaceholder(modifier = Modifier.size(Height))
        }
    }
}

@Composable
fun ImagePlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.placeholder(),
    )
}

@Composable
fun DescriptionPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp).placeholder(),
    )
}

@Composable
fun TitlePlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Plceholder title",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.placeholder()
        )
        Text(
            text = "Placeholder category",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.placeholder(),
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
fun TagsPlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Теги",
            style = MaterialTheme.typography.titleMedium,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            repeat(5) {
                Chip("placeholder", modifier = Modifier.placeholder())
            }
        }
    }
}

@Composable
fun ReviewsPlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ReviewsHeaderPlaceholder(
            modifier = Modifier.fillMaxWidth()
        )
        ReviewPlaceholder()
    }
}

@Composable
fun ReviewsHeaderPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = "Отзывы",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RatingPlaceholder()
        }
    }
}

@Composable
fun ReviewPlaceholder() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).placeholder(),
    )
}

@Composable
fun RatingPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.placeholder(),
    ) {
        Image(
            UIKitIconPack.RatingStar,
            contentDescription = null,
            modifier = Modifier.size(UIKitSizeTokens.SmallIconSize),
        )
        Text("4.5")
    }
}
