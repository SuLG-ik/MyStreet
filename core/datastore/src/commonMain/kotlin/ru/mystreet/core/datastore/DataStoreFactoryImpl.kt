package ru.mystreet.core.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.KSerializer

internal class DataStoreFactoryImpl(
    private val pathProvider: DataStorePathProvider,
) : DatastoreFactory {

    override fun createPreferences(
        name: String,
        corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
        migrations: List<DataMigration<Preferences>>,
        scope: CoroutineScope,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = corruptionHandler,
            migrations = migrations,
            scope = scope,
            produceFile = { pathProvider.providePath(name) }
        )
    }

    override fun <T : Any> create(
        name: String,
        initialValue: T,
        serializer: KSerializer<T>,
        corruptionHandler: ReplaceFileCorruptionHandler<T>?,
        migrations: List<DataMigration<T>>,
        scope: CoroutineScope,
    ): DataStore<T> {
        return SerializationDataStore.create(
            initialValue = initialValue,
            serializer = serializer,
            corruptionHandler = corruptionHandler,
            migrations = migrations,
            scope = scope,
            produceFile = { pathProvider.providePath(name) },
        )
    }


}