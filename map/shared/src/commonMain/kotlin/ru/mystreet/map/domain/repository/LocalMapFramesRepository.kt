package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.entity.SavedMapFrame

interface LocalMapFramesRepository {

    suspend fun getLoadedMapFrame(frame: MapFrame): SavedMapFrame?

    suspend fun saveLoadedMapFrame(frame: MapFrame)

}