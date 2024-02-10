package ru.mystreet.map.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.EditMapBottomBar

@Composable
fun EditMapBottomBarUI(
    component: EditMapBottomBar,
    modifier: Modifier,
) {
    AnimatedVisibility(
        component.isVisible.subscribeAsState().value,
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it }) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Children(component.childStack) {
                EditMapBottomBarNavHost(
                    child = it.instance,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            FilledTonalButton(
                onClick = component::onContinue,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Сохранить")
            }
        }
    }
}

@Composable
fun EditMapBottomBarNavHost(child: EditMapBottomBar.Child, modifier: Modifier) {
    when (child) {
        is EditMapBottomBar.Child.SelectCategory ->
            EditMapSelectCategoryUI(child.component, modifier = modifier)
    }
}