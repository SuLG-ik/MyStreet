package ru.mystreet.map.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.component.add.EditMapNewObjectLoading

@Composable
fun EditMapNewObjectLoadingUI(component: EditMapNewObjectLoading, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
    ) {
        Text("Добавление объекта...")
    }
}