package ru.mystreet.map.root.ui.external

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.mapobject.ui.edit.MapObjectInfoHostUI
import ru.mystreet.map.root.component.external.MapExternalHostComponent

@Composable
fun MapExternalHostUI(component: MapExternalHostComponent, modifier: Modifier = Modifier) {
    MapObjectInfoHostUI(component.mapObject, modifier)
}