package ru.mystreet.map.image

import dev.icerock.moko.resources.ImageResource

actual class ImageProviderFactory {
    actual fun forResource(
        imageResource: ImageResource,
        cacheable: Boolean
    ): ImageProvider {
        return ImageProvider(imageResource)
    }
}