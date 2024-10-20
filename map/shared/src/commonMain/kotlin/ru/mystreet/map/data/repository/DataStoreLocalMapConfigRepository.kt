package ru.mystreet.map.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import ru.mystreet.core.datastore.DatastoreFactory
import ru.mystreet.core.datastore.create
import ru.mystreet.map.domain.entity.CameraPositionConfig
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.repository.LocalMapConfigRepository

@Serializable
data class LocalMapConfig(
    val mapConfig: MapConfig = MapConfig(initialCameraPosition = null),
)

class DataStoreLocalMapConfigRepository(
    dataStoreFactory: DatastoreFactory,
) : LocalMapConfigRepository {

    private val dataStore =
        dataStoreFactory.create(name = "map_config", initialValue = LocalMapConfig())

    override val mapConfig: Flow<MapConfig> = dataStore.data.map { it.mapConfig }

    override suspend fun updateInitialCameraPosition(cameraPosition: CameraPositionConfig) {
        dataStore.updateData { it.copy(mapConfig = it.mapConfig.copy(initialCameraPosition = cameraPosition)) }
    }

}

