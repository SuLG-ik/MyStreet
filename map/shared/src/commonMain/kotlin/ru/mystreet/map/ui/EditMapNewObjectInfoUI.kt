package ru.mystreet.map.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.component.EditMapNewObjectInfo
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitOutlineTextField
import ru.mystreet.uikit.UIKitOutlineTextFieldWithChips
import ru.mystreet.uikit.UIKitTitledSurfaceColumn
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

@OptIn(ExperimentalLayoutApi::class)
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
                value = field.title,
                onValueChange = onTitleInput,
                enabled = true,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitOutlineTextField(
                stringResource(ru.mystreet.map.MR.strings.map_edit_add_description_field),
                value = field.description,
                onValueChange = onDescriptionInput,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitOutlineTextFieldWithChips(
                stringResource(ru.mystreet.map.MR.strings.map_edit_add_tags_field),
                value = field.tags.tag,
                placeholder = stringResource(ru.mystreet.map.MR.strings.map_edit_add_tags_placeholder),
                completePainter = painterResource(MR.images.add),
                chips = field.tags.tags,
                chip = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clip(CircleShape).clickable {
                            onTagRemove(it)
                        }.border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        ).padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(imageResource = MR.images.remove),
                            contentDescription = null,
                            modifier = Modifier.size(UIKitSizeTokens.SmallIconSize)
                        )
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier,
                        )
                    }
                },
                supportingText = {
                    Text("${field.tags.tags.size}/${field.tags.maxTags}")
                },
                enabled = field.tags.isInputAvailable,
                onValueChange = onTagInput,
                onComplete = onTagAdd,
                modifier = Modifier.fillMaxWidth(),
            )

        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Добавить")
        }
    }
}