package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.domain.entity.MapObject

interface MapObjectsStore :
    Store<MapObjectsStore.Intent, MapObjectsStore.State, MapObjectsStore.Label> {

    sealed interface Intent {
        data class UpdateCameraPosition(val cameraPosition: CameraPosition) : Intent
    }

    data class State(
        val loadedMapObjects: List<MapObject> = emptyList(),
    )

    sealed interface Label {
        data class OnMapObjectsLoaded(val loadedMapObjects: List<MapObject>) : Label
    }

}