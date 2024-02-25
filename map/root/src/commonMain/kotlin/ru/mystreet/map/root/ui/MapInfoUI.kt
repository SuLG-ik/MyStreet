package ru.mystreet.map.root.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.root.component.MapInfo
import ru.mystreet.map.ui.MapObjectInfoHostUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapInfoUI(
    component: MapInfo,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    val isExpanded by component.isExpanded.subscribeAsState()
    MapInfoScreen(
        isExpanded = isExpanded,
        onDismiss = component::onDismiss,
        sheetContent = {
            val slots = component.childSlot.subscribeAsState()
            slots.value.child?.instance?.let {
                MapInfoNavHost(
                    child = it,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        modifier = modifier,
        content = content,
    )
}

@Composable
fun MapInfoNavHost(
    child: MapInfo.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapInfo.Child.MapObjectInfo -> MapObjectInfoHostUI(child.component, modifier)
    }
}