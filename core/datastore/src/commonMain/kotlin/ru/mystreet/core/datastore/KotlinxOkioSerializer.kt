package ru.mystreet.core.datastore

import androidx.datastore.core.okio.OkioSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.json.okio.encodeToBufferedSink
import okio.BufferedSink
import okio.BufferedSource

internal class KotlinxOkioSerializer<T : Any>(
    override val defaultValue: T,
    private val serializer: KSerializer<T>,
    private val json: Json,
) : OkioSerializer<T> {


    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun readFrom(source: BufferedSource): T {
        return json.decodeFromBufferedSource(serializer, source)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun writeTo(t: T, sink: BufferedSink) {
        json.encodeToBufferedSink(serializer, t, sink)
    }


}