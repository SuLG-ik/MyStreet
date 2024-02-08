package ru.mystreet.core.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope

class PlatformDataStoreFactory(
    private val context: Context
) : DatastoreFactory {

    override fun create(
        corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
        migrations: List<DataMigration<Preferences>>,
        scope: CoroutineScope,
        name: String,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = corruptionHandler,
            migrations = migrations,
            scope = scope,
            produceFile = { context.dataDir.resolve("$name.preferences_pb") }
        )
    }

}