package ru.mystreet.map.root.component.external

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.account.ui.AccountHostUI
import ru.mystreet.bottomsheet.child.SheetChildContent
import ru.mystreet.map.ui.MapObjectInfoHostUI

class MapExternalSheetChildContent : SheetChildContent<MapExternalSheetHost.Child> {
    @Composable
    override fun Content(child: MapExternalSheetHost.Child?, modifier: Modifier) {
        when (child) {
            is MapExternalSheetHost.Child.Account -> AccountHostUI(child.component, modifier)
            is MapExternalSheetHost.Child.MapObjectInfo -> MapObjectInfoHostUI(
                component = child.component,
                modifier = modifier
            )
            null -> {}
        }
    }

}