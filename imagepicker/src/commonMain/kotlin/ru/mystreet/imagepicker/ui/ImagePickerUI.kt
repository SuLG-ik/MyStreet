package ru.mystreet.imagepicker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.imagepicker.component.ImagePicker

@Composable
fun ImagePickerUI(
    component: ImagePicker,
    modifier: Modifier,
) {
    ImagePickerScreen(
        isContinueAvailable = component.isContinueAvailable.subscribeAsState().value,
        images = component.images.subscribeAsState().value,
        onLoad = component::onLoad,
        onBack = component::onBack,
        onPick = component::onPick,
        onRemove = component::onRemove,
        modifier = modifier
    )
}