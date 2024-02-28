package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory

interface MapObjectsStore :
    Store<MapObjectsStore.Intent, MapObjectsStore.State, MapObjectsStore.Label> {

    sealed interface Intent {
        data class SetMapObjectCategories(
            val categories: List<MapObjectCategory>,
        ) : Intent

        data class UpdateCameraPosition(val cameraPosition: CameraPosition) : Intent
    }

    data class State(
        val categories: List<MapObjectCategory>? = null,
        val loadedMapObjects: List<MapObject> = emptyList(),
    )

    sealed interface Label {
        data class OnMapObjectsLoaded(val loadedMapObjects: List<MapObject>) : Label
        data class OnRemoveMapObjectsCategories(val categories: List<MapObjectCategory>) : Label
    }

}