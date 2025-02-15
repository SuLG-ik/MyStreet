package ru.mystreet.map.mapobject.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.app.feature.dialogs.ui.ModalDialogUI
import ru.mystreet.imagepicker.ui.ImagePickerUI
import ru.mystreet.map.mapobject.component.info.MapObjectInfoHostComponent
import ru.mystreet.map.mapobject.ui.info.MapObjectInfoUI
import ru.mystreet.map.mapobject.ui.info.MapObjectReviewAddUI
import ru.mystreet.map.ui.edit.MapObjectEditUI
import ru.mystreet.uikit.UIKitChildren

@Composable
fun MapObjectInfoHostUI(
    component: MapObjectInfoHostComponent,
    modifier: Modifier = Modifier,
) {
    ModalDialogUI(component) {
        UIKitChildren(component.childStack) {
            MapObjectInfoNavHost(it.instance, modifier)
        }
    }
}

@Composable
fun MapObjectInfoNavHost(
    child: MapObjectInfoHostComponent.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapObjectInfoHostComponent.Child.AddImage -> ImagePickerUI(child.component, modifier)
        is MapObjectInfoHostComponent.Child.Info -> MapObjectInfoUI(child.component, modifier)
        is MapObjectInfoHostComponent.Child.Edit -> MapObjectEditUI(child.component, modifier)
        is MapObjectInfoHostComponent.Child.AddReview -> MapObjectReviewAddUI(
            child.component,
            modifier
        )

        MapObjectInfoHostComponent.Child.Empty -> {}
    }
}