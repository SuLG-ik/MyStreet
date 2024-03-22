package ru.mystreet.map.ui.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.info.MapObjectInfo

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
        onImagePicker = component::onImagePicker,
        onFavourite = component::onFavourite,
        onEdit = component::onEdit,
        modifier = modifier
    )
}

