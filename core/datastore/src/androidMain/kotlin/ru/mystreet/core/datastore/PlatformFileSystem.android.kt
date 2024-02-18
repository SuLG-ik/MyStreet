package ru.mystreet.core.datastore

import okio.FileSystem

internal actual object PlatformFileSystem {

    actual val SYSTEM: FileSystem = okio.FileSystem.SYSTEM

}