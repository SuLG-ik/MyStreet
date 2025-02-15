package ru.mystreet.map.root.ui.external

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.account.ui.AccountHostUI
import ru.mystreet.map.mapobject.ui.edit.MapObjectInfoHostUI
import ru.mystreet.map.root.component.external.MapExternalHostComponent

@Composable
fun MapExternalHostUI(component: MapExternalHostComponent) {
    MapObjectInfoHostUI(component.mapObject, Modifier.fillMaxWidth())
    AccountHostUI(component.accountHost, Modifier.fillMaxWidth())
}