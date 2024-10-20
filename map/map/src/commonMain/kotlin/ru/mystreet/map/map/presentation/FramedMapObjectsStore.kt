package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.VisibleRegion

interface FramedMapObjectsStore :
    Store<FramedMapObjectsStore.Intent, FramedMapObjectsStore.State, FramedMapObjectsStore.Label> {

    sealed interface Intent {
        data class UpdateCameraPosition(
            val visibleArea: VisibleRegion,
            val cameraPosition: CameraPosition,
        ) : Intent
    }

    data class State(
        val loadedFrames: List<MapFrame> = emptyList(),
        val loadedObjects: List<FramedMapObjects> = emptyList(),
    )

    sealed interface Label {
        data class OnLoaded(val objects: FramedMapObjects) : Label
    }

}