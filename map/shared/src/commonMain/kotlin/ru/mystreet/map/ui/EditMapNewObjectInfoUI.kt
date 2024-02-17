package ru.mystreet.map.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.EditMapNewObjectInfo
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.uikit.UIKitOutlineTextField

@Composable
fun EditMapNewObjectInfoUI(component: EditMapNewObjectInfo, modifier: Modifier = Modifier) {
    val field by component.field.subscribeAsState()
    EditMapNewObjectScreen(
        field = field,
        onTitleInput = component::onTitleInput,
        onDescriptionInput = component::onDescriptionInput,
        onContinue = component::onContinue,
        modifier = modifier
    )
}

@Composable
private fun EditMapNewObjectScreen(
    field: AddMapObjectField,
    onTitleInput: (String) -> Unit,
    onDescriptionInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(15.dp).fillMaxWidth(),
            ) {
                Text("Добавление объекта (${field.point})")
                UIKitOutlineTextField(
                    "Название",
                    value = field.title,
                    onValueChange = onTitleInput,
                    enabled = true,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                UIKitOutlineTextField(
                    "Описание",
                    value = field.description,
                    onValueChange = onDescriptionInput,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Добавить")
        }
    }
}