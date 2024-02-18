package ru.mystreet.core.datastore

import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

internal actual class DataStorePathProvider {

    @OptIn(ExperimentalForeignApi::class)
    actual fun providePath(name: String): Path {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )

        return (requireNotNull(documentDirectory).path + "/$name.preferences_pb").toPath()
    }

}