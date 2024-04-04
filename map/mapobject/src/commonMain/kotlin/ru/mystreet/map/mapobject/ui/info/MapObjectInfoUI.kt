package ru.mystreet.map.mapobject.ui.info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.mapobject.component.info.MapObjectInfo

@Composable
fun MapObjectInfoUI(
    component: MapObjectInfo,
    modifier: Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val mapObject by component.mapObjectInfo.subscribeAsState()
    MapObjectInfoScreen(
        reviews = {
            MapObjectReviewsUI(
                component = component.reviews,
                modifier = Modifier.fillMaxWidth()
            )
        },
        isLoading = isLoading,
        mapObject = mapObject.value,
        onImagePicker = component::onImagePicker,
        onFavourite = component::onFavourite,
        onAddReview = component::onAddReview,
        onEdit = component::onEdit,
        modifier = modifier
    )
}

