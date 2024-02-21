package ru.mystreet.core.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.core.okio.OkioStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import okio.Path

internal object SerializationDataStore {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = false
    }

    fun <T : Any> create(
        produceFile: () -> Path,
        initialValue: T,
        serializer: KSerializer<T>,
        corruptionHandler: ReplaceFileCorruptionHandler<T>?,
        migrations: List<DataMigration<T>>,
        scope: CoroutineScope,
    ): DataStore<T> {
        return DataStoreFactory.create(
            storage = OkioStorage(
                PlatformFileSystem.SYSTEM,
                KotlinxOkioSerializer(initialValue, serializer, json)
            ) {
                produceFile()
            },
            corruptionHandler = corruptionHandler,
            migrations = migrations,
            scope = scope
        )

    }

}