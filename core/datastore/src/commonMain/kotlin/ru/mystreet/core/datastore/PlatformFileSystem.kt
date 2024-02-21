package ru.mystreet.core.datastore

import okio.FileSystem

internal expect object PlatformFileSystem {

    val SYSTEM: FileSystem

}