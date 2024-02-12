package ru.mystreet.map.general.data.repository

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import ru.mystreet.core.datastore.DatastoreFactory
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralLayerType
import ru.mystreet.map.general.domain.repository.LayersConfigRepository

class DataStoreLayersConfigRepositoryImpl(
    dataStoreFactory: DatastoreFactory,
) : LayersConfigRepository {

    private val store = dataStoreFactory.create(name = "layers_config")

    override val layersConfig = store.data.map { it.toGeneralLayers() }

    override suspend fun setLayerEnabled(layer: GeneralLayerType, enabled: Boolean) {
        store.edit { it[preferenceEnabledKeyByType(layer)] = enabled }
    }
}

private fun preferenceEnabledKeyByType(type: GeneralLayerType): Preferences.Key<Boolean> {
    return booleanPreferencesKey("layer_${type.id}_enabled")
}

private fun Preferences.get(type: GeneralLayerType): Boolean {
    return get(preferenceEnabledKeyByType(type)) ?: type.isEnabledByDefault
}

private fun Preferences.toGeneralLayers(): List<GeneralLayer> {
    return enumValues<GeneralLayerType>().mapIndexed { index, type ->
        GeneralLayer(
            isEnabled = get(type),
            position = index + 1,
            type = type,
        )
    }
}

