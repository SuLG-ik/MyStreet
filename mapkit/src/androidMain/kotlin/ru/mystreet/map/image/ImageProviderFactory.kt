package ru.mystreet.map.image

import android.content.Context
import dev.icerock.moko.resources.ImageResource

actual class ImageProviderFactory(
    private val context: Context,
) {
    actual fun forResource(imageResource: ImageResource, cacheable: Boolean): ImageProvider {
        return ImageProvider(context, imageResource, cacheable)
    }
}