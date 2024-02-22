package ru.mystreet.map.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.MR
import ru.mystreet.map.component.EditMapNewObjectInfo
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.entity.FieldSuggestion
import ru.mystreet.uikit.UIKitOutlineTextField
import ru.mystreet.uikit.UIKitOutlineTextFieldWithChips
import ru.mystreet.uikit.UIKitTitledSurfaceColumn
import ru.mystreet.uikit.clickableIfNoNull
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun EditMapNewObjectInfoUI(component: EditMapNewObjectInfo, modifier: Modifier = Modifier) {
    val field by component.field.subscribeAsState()
    EditMapNewObjectScreen(
        field = field,
        onTitleInput = component::onTitleInput,
        onDescriptionInput = component::onDescriptionInput,
        onTagAdd = component::onTagAdd,
        onTagInput = component::onTagInput,
        onContinue = component::onContinue,
        onTagRemove = component::onTagRemove,
        onBack = component::onBack,
        modifier = modifier
    )
}

@Composable
private fun EditMapNewObjectScreen(
    field: AddMapObjectField,
    onTitleInput: (String) -> Unit,
    onDescriptionInput: (String) -> Unit,
    onTagInput: (String) -> Unit,
    onTagAdd: () -> Unit,
    onTagRemove: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        UIKitTitledSurfaceColumn(
            title = stringResource(ru.mystreet.map.MR.strings.map_edit_add_title),
            onBack = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            UIKitOutlineTextField(
                stringResource(ru.mystreet.map.MR.strings.map_edit_add_title_field),
                value = field.title.value,
                onValueChange = onTitleInput,
                enabled = true,
                singleLine = true,
                isError = field.title.error != null,
                errorText = {
                    TitleError(field.title.error)
                },
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitOutlineTextField(
                stringResource(MR.strings.map_edit_add_description_field),
                value = field.description,
                onValueChange = onDescriptionInput,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )
            var isTagsSuggestionsExpanded by rememberSaveable { mutableStateOf(false) }
            TagsSuggestion(
                isExpanded = field.tags.value.isNotBlank() && isTagsSuggestionsExpanded,
                suggestion = field.tags.suggestion,
                onTagInput = {
                    onTagInput(it)
                    onTagAdd()
                },
                onDismissRequest = {
                    isTagsSuggestionsExpanded = false
                },
                supportingText = {
                    Text("${field.tags.tags.tags.size}/${field.tags.tags.maxTags}")
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                UIKitOutlineTextFieldWithChips(
                    stringResource(MR.strings.map_edit_add_tags_field),
                    value = field.tags.value,
                    placeholder = stringResource(MR.strings.map_edit_add_tags_placeholder),
                    completePainter = painterResource(ru.mystreet.uikit.MR.images.add),
                    chips = field.tags.tags.tags,
                    chip = {
                        Chip(
                            text = it,
                            icon = painterResource(imageResource = ru.mystreet.uikit.MR.images.remove),
                            onClick = { onTagRemove(it) },
                            modifier = Modifier,
                        )
                    },
                    enabled = field.tags.isInputAvailable,
                    onValueChange = {
                        isTagsSuggestionsExpanded = true
                        onTagInput(it)
                    },
                    onComplete = onTagAdd,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(MR.strings.map_edit_add_continue_button))
        }
    }
}

@Composable
fun Chip(
    text: String,
    icon: Painter? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clip(CircleShape).clickableIfNoNull(onClick = onClick).border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = CircleShape
        ).padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.VerySmallIconSize)
            )
        }
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSuggestion(
    isExpanded: Boolean,
    suggestion: FieldSuggestion<String>,
    onDismissRequest: () -> Unit,
    onTagInput: (String) -> Unit,
    supportingText: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        content()
        AnimatedVisibility(isExpanded && suggestion.suggestions.isNotEmpty()) {
            Column {
                Text("Предложенные теги", style = MaterialTheme.typography.titleMedium)
                FlowRow {
                    suggestion.suggestions.forEach {
                        TagSuggestionItem(
                            text = it,
                            onClick = {
                                onDismissRequest()
                                onTagInput(it)
                            },
                        )
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.titleSmall) {
                supportingText()
            }
        }
    }
}

@Composable
fun TagSuggestionItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Chip(
        text = text,
        icon = painterResource(ru.mystreet.uikit.MR.images.add),
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun TitleError(
    error: FieldError?,
    modifier: Modifier = Modifier,
) {
    if (error != null)
        Text(
            text = error.formatTitleError(),
            modifier = modifier,
        )
}

private fun FieldError.formatTitleError(): String {
    return when (this) {
        FieldError.FieldIsEmpty -> "Поле является обязательным"
        FieldError.IncorrectInput -> "Можно использовать только кириллицу, латиницу и символы - , ."
    }
}
