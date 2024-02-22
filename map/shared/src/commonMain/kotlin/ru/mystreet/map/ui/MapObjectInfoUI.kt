package ru.mystreet.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.MapObjectInfo

@Composable
fun MapObjectInfoUI(
    component: MapObjectInfo,
    modifier: Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val mapObject by component.mapObjectInfo.subscribeAsState()
    MapObjectInfoScreen(
        isLoading = isLoading,
        mapObject = mapObject.value,
        modifier = modifier
    )
}

