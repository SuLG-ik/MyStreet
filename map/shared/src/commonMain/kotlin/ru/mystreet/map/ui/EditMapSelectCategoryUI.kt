package ru.mystreet.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.EditMapSelectCategory

@Composable
fun EditMapSelectCategoryUI(
    component: EditMapSelectCategory,
    modifier: Modifier,
) {
    val categories by component.selectedCategories.subscribeAsState()
    EditMapSelectCategoryScreen(
        categories = categories,
        onSelect = component::onSelect,
        onContinue = component::onContinue,
        modifier = modifier,
    )
}