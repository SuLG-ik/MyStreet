package ru.mystreet.map.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.domain.entity.SelectableCategory
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitSelectableTonalIconButton
import ru.mystreet.uikit.paddingHorizontalInsets
import ru.mystreet.uikit.tokens.UIKitSizeTokens
import ru.mystreet.uikit.whiteBottom

enum class SelectableCategoryInfo(
    val title: StringResource,
    val image: ImageResource,
) {
    Bench(MR.strings.bench_full, MR.images.bench),
    Bower(MR.strings.bower_full, MR.images.bower),
    Fountain(MR.strings.fountain_full, MR.images.fountain),
    GreenArea(MR.strings.green_zone_full, MR.images.green_zone),
    Monument(MR.strings.monument_full, MR.images.monument),
    Playground(MR.strings.playground_full, MR.images.playground),
    PublicWC(MR.strings.public_wc_full, MR.images.public_wc),
    StreetLight(MR.strings.streetlight_full, MR.images.streetlight),
    Trash(MR.strings.trash_full, MR.images.trash),
}

@Composable
fun EditMapSelectCategoryScreen(
    categories: List<SelectableCategory>,
    onSelect: (MapObjectCategory) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(275.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier
                    .whiteBottom(scrollState)
                    .verticalScroll(scrollState)
            ) {
                categories.forEach {
                    SelectableCategory(
                        isSelected = it.isSelected,
                        category = it.type.toUI(),
                        onSelect = { onSelect(it.type) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Продолжить")
        }
    }
}

@Composable
fun SelectableCategory(
    isSelected: Boolean,
    category: SelectableCategoryInfo,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(),
            onClick = onSelect
        ).padding(horizontal = 5.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        UIKitSelectableTonalIconButton(
            selected = isSelected,
            onClick = onSelect,
            shape = MaterialTheme.shapes.medium,
            unselectedColor = MaterialTheme.colorScheme.secondaryContainer,
            interactionSource = interactionSource,
        ) {
            Icon(
                painterResource(category.image),
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
            )
        }
        Text(stringResource(category.title), maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

fun MapObjectCategory.toUI(): SelectableCategoryInfo {
    return when (this) {
        MapObjectCategory.Bench -> SelectableCategoryInfo.Bench
        MapObjectCategory.Playground -> SelectableCategoryInfo.Playground
        MapObjectCategory.StreetLight -> SelectableCategoryInfo.StreetLight
        MapObjectCategory.Monument -> SelectableCategoryInfo.Monument
        MapObjectCategory.Fountain -> SelectableCategoryInfo.Fountain
        MapObjectCategory.Bower -> SelectableCategoryInfo.Bower
        MapObjectCategory.GreenArea -> SelectableCategoryInfo.GreenArea
        MapObjectCategory.PublicWC -> SelectableCategoryInfo.PublicWC
        MapObjectCategory.Trash -> SelectableCategoryInfo.Trash
    }
}
