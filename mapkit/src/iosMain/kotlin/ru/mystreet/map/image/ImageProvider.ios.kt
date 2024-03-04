package ru.mystreet.map.image

import dev.icerock.moko.resources.ImageResource
import platform.UIKit.UIImage

actual class ImageProvider(
    private val imageResource: ImageResource,
) {

    fun toNative(): UIImage? {
        return imageResource.toUIImage()
    }

}