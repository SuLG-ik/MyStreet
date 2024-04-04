package ru.mystreet.map.mapobject.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.mapobject.component.info.MapObjectReviewAdd
import ru.mystreet.map.ui.TitleError
import ru.mystreet.uikit.KeyboardActionsNext
import ru.mystreet.uikit.KeyboardOptionsNext
import ru.mystreet.uikit.UIKitTitledSurfaceColumn
import ru.mystreet.uikit.UIKitValidatedOutlinedTextField

@Composable
fun MapObjectReviewAddUI(
    component: MapObjectReviewAdd,
    modifier: Modifier = Modifier,
) {
    val field by component.field.subscribeAsState()
    MapObjectReviewAddScreen(
        field = field,
        onContinue = component::onContinue,
        onBack = component::onBack,
        onTitleInput = component::onTitleInput,
        onDescriptionInput = component::onContentInput,
        modifier = modifier,
    )
}

@Composable
fun MapObjectReviewAddScreen(
    field: AddMapObjectReviewField,
    onTitleInput: (String) -> Unit,
    onDescriptionInput: (String) -> Unit,
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
        ) {
            UIKitValidatedOutlinedTextField(
                title = "Тема",
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
                value = field.content,
                onValueChange = onDescriptionInput,
                minLines = 3,
                maxLines = 6,
                keyboardOptions = KeyboardOptionsNext,
                keyboardActions = KeyboardActionsNext,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        FilledTonalButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        ) {
            Text("Добавить")
        }
    }
}