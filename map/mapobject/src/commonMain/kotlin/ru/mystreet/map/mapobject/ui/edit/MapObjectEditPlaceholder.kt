package ru.mystreet.map.ui.edit

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.UIKitTextFieldPlaceholder
import ru.mystreet.uikit.UIKitTitledSurfaceColumn


@Composable
fun MapObjectEditPlaceholder(
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 10.dp).padding(WindowInsets.ime.asPaddingValues())
            .verticalScroll(rememberScrollState())
    ) {
        UIKitTitledSurfaceColumn(
            title = "Редактирование",
            actions = {
            },
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            UIKitTextFieldPlaceholder(
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitTextFieldPlaceholder(
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitTextFieldPlaceholder(
                modifier = Modifier.fillMaxWidth(),
            )
            UIKitTextFieldPlaceholder(
                modifier = Modifier.fillMaxWidth(),
            )
        }
        FilledTonalButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        ) {
            Text("Сохранить")
        }
    }
}