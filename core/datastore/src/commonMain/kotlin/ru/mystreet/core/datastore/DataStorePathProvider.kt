package ru.mystreet.core.datastore

import okio.Path

internal expect class DataStorePathProvider {

    fun providePath(name: String): Path

}