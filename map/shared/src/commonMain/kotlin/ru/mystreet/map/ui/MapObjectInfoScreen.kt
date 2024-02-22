package ru.mystreet.map.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.domain.entity.MapObject

@Composable
fun MapObjectInfoScreen(
    isLoading: Boolean,
    mapObject: MapObject?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (isLoading) {
            Text("Загрузка")
        } else {
            if (mapObject != null) {
                Text(mapObject.title)
                mapObject.tags.forEach {
                    Chip(it.title)
                }
            }
        }
    }
}