package ru.mystreet.core.datastore

import android.content.Context
import okio.Path
import okio.Path.Companion.toOkioPath

internal actual class DataStorePathProvider(private val context: Context) {

    actual fun providePath(name: String): Path {
        return context.dataDir.resolve("$name.preferences_pb").absoluteFile.toOkioPath()
    }

}