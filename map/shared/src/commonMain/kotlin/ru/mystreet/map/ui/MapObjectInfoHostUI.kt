package ru.mystreet.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.mystreet.imagepicker.ui.ImagePickerUI
import ru.mystreet.map.component.info.MapObjectInfoHost

@Composable
fun MapObjectInfoHostUI(
    component: MapObjectInfoHost,
    modifier: Modifier = Modifier,
) {
    Children(component.childStack) {
        MapObjectInfoNavHost(it.instance, modifier)
    }
}

@Composable
fun MapObjectInfoNavHost(
    child: MapObjectInfoHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapObjectInfoHost.Child.AddImage -> ImagePickerUI(child.component, modifier)
        is MapObjectInfoHost.Child.Info -> MapObjectInfoUI(child.component, modifier)
    }
}