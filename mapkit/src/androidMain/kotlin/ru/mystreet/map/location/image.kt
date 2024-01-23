package ru.mystreet.map.location

import android.content.Context
import com.yandex.runtime.image.ImageProvider
import dev.icerock.moko.resources.ImageResource

fun ImageResource.toImageProvider(context: Context, cache: Boolean = true): ImageProvider {
    return ImageProvider.fromResource(context, drawableResId, cache)
}