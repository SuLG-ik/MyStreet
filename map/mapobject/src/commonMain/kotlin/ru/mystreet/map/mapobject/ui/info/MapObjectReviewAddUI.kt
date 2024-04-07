package ru.mystreet.map.mapobject.ui.info

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.mapobject.component.info.MapObjectReviewAdd
import ru.mystreet.map.ui.TitleError
import ru.mystreet.uikit.KeyboardActionsNext
import ru.mystreet.uikit.KeyboardOptionsNext
import ru.mystreet.uikit.UIKitSwitch
import ru.mystreet.uikit.UIKitTitledSurfaceColumn
import ru.mystreet.uikit.UIKitValidatedOutlinedTextField
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStarOutline

@Composable
fun MapObjectReviewAddUI(
    component: MapObjectReviewAdd,
    modifier: Modifier = Modifier,
) {
    val field by component.field.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val isLoading by component.isLoading.subscribeAsState()
    MapObjectReviewAddScreen(
        field = field,
        isContinueAvailable = isContinueAvailable,
        isLoading = isLoading,
        onContinue = component::onContinue,
        onBack = component::onBack,
        onTitleInput = component::onTitleInput,
        onDescriptionInput = component::onContentInput,
        onRatingInput = component::onRatingInput,
        onAuthorHiddenInput = component::onAuthorHiddenInput,
        modifier = modifier,
    )
}

@Composable
fun MapObjectReviewAddScreen(
    field: AddMapObjectReviewField,
    isContinueAvailable: Boolean,
    isLoading: Boolean,
    onTitleInput: (String) -> Unit,
    onDescriptionInput: (String) -> Unit,
    onRatingInput: (Int) -> Unit,
    onAuthorHiddenInput: (Boolean) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 10.dp).padding(WindowInsets.ime.asPaddingValues())
            .verticalScroll(rememberScrollState())
    ) {
        UIKitTitledSurfaceColumn(
            title = "Создание отзыва",
            onBack = onBack,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            ScoreSelector(
                rating = field.rating,
                onRatingInput = onRatingInput,
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitValidatedOutlinedTextField(
                title = "Тема",
                placeholder = { Text("Не обязательно") },
                value = field.title,
                onValueChange = onTitleInput,
                enabled = true,
                singleLine = true,
                keyboardOptions = KeyboardOptionsNext,
                keyboardActions = KeyboardActionsNext,
                errorText = {
                    TitleError(it)
                },
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitValidatedOutlinedTextField(
                title = "Содержание",
                placeholder = { Text("Не обязательно") },
                value = field.content,
                onValueChange = onDescriptionInput,
                minLines = 3,
                maxLines = 6,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitSwitch(
                label = "Скрыть моё имя",
                checked = field.isAuthorHidden,
                onCheckedChange = { onAuthorHiddenInput(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        FilledTonalButton(
            enabled = isContinueAvailable,
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        ) {
            Text("Добавить")
        }
    }
}

@Composable
fun ScoreSelector(
    rating: Int,
    onRatingInput: (Int) -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        repeat(5) {
            RatingScore(
                isSelected = it + 1 <= rating,
                index = it + 1,
                onSelect = { onRatingInput(it + 1) },
                modifier = Modifier.size(50.dp),
            )
        }
    }
}

@Composable
fun RatingScore(
    isSelected: Boolean,
    index: Int = 1,
    onSelect: () -> Unit,
    modifier: Modifier,
) {
    val scale = rememberStarScale(isSelected, index)
    Box(
        modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onSelect
            )
    ) {
        Crossfade(isSelected) {
            if (it) {
                Icon(
                    UIKitIconPack.RatingStar,
                    contentDescription = null,
                    tint = Color(0xFFEEA63A),
                    modifier = modifier
                )
            } else {
                Icon(
                    UIKitIconPack.RatingStarOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun rememberStarScale(isSelected: Boolean, index: Int): Float {
    val scale = remember { Animatable(1f, Float.VectorConverter) }
    var previousIsSelected by remember { mutableStateOf(isSelected) }
    LaunchedEffect(isSelected) {
        try {
            if (isSelected != previousIsSelected) {
                previousIsSelected = isSelected
                if (isSelected) {
                    delay(25L * (index - 1))
                    scale.animateTo(1.2f)
                    scale.animateTo(
                        1f,
                        spring(
                            dampingRatio = 0.3f,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    )
                }
            }
        } catch (e: CancellationException) {
            scale.snapTo(1f)
        }
    }
    return scale.value
}
