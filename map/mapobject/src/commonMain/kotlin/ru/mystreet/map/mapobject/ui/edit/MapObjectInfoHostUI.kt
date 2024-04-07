package ru.mystreet.map.mapobject.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.imagepicker.ui.ImagePickerUI
import ru.mystreet.map.mapobject.component.info.MapObjectInfoHost
import ru.mystreet.map.mapobject.ui.info.MapObjectInfoUI
import ru.mystreet.map.mapobject.ui.info.MapObjectReviewAddUI
import ru.mystreet.map.ui.edit.MapObjectEditUI
import ru.mystreet.uikit.UIKitChildren

@Composable
fun MapObjectInfoHostUI(
    component: MapObjectInfoHost,
    modifier: Modifier = Modifier,
) {
    UIKitChildren(component.childStack) {
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
        is MapObjectInfoHost.Child.Edit -> MapObjectEditUI(child.component, modifier)
        is MapObjectInfoHost.Child.AddReview -> MapObjectReviewAddUI(child.component, modifier)
    }
}