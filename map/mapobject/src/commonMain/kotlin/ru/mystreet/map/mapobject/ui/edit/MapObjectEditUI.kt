package ru.mystreet.map.ui.edit

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.MR
import ru.mystreet.map.component.edit.MapObjectEdit
import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.ui.TagsInput
import ru.mystreet.map.ui.TitleError
import ru.mystreet.uikit.KeyboardActionsNext
import ru.mystreet.uikit.KeyboardOptionsNext
import ru.mystreet.uikit.UIKitTitledSurfaceColumn
import ru.mystreet.uikit.UIKitValidatedOutlinedTextField
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Trash
import ru.mystreet.uikit.paddingIme
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun MapObjectEditUI(component: MapObjectEdit, modifier: Modifier = Modifier) {
    Crossfade(component.isLoading.subscribeAsState().value) {
        if (it) {
            MapObjectEditPlaceholder(modifier = modifier)
        } else {
            val field by component.field.subscribeAsState()
            val fieldValue = field.value
            if (fieldValue != null)
                MapObjectEditScreen(
                    field = fieldValue,
                    onTitleInput = component::onTitleInput,
                    onDescriptionInput = component::onDescriptionInput,
                    onTagAdd = component::onTagAdd,
                    onTagInput = component::onTagInput,
                    onContinue = component::onContinue,
                    onTagRemove = component::onTagRemove,
                    onBack = component::onBack,
                    onDelete = component::onDelete,
                    modifier = modifier
                )
        }
    }
}

@Composable
private fun MapObjectEditScreen(
    field: EditMapObjectField,
    onTitleInput: (String) -> Unit,
    onDescriptionInput: (String) -> Unit,
    onTagInput: (String) -> Unit,
    onTagAdd: () -> Unit,
    onTagRemove: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 10.dp).paddingIme()
            .verticalScroll(rememberScrollState())
    ) {
        UIKitTitledSurfaceColumn(
            title = "Редактирование",
            onBack = onBack,
            actions = {
                DeleteAction(onDelete = onDelete)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            UIKitValidatedOutlinedTextField(
                stringResource(MR.strings.map_edit_add_title_field),
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
                stringResource(MR.strings.map_edit_add_description_field),
                value = field.description,
                onValueChange = onDescriptionInput,
                singleLine = true,
                keyboardOptions = KeyboardOptionsNext,
                keyboardActions = KeyboardActionsNext,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )
            TagsInput(
                field = field.tags,
                onTagInput = onTagInput,
                onTagAdd = onTagAdd,
                onTagRemove = onTagRemove,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        ) {
            Text("Сохранить")
        }
    }
}


@Composable
fun DeleteAction(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onDelete,
        colors = iconButtonColors(contentColor = MaterialTheme.colorScheme.error),
        modifier = modifier,
    ) {
        Icon(
            UIKitIconPack.Trash,
            contentDescription = null,
            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
        )
    }
}