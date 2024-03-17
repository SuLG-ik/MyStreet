package ru.mystreet.map.image

import dev.icerock.moko.resources.ImageResource

expect class ImageProviderFactory {

    fun forResource(imageResource: ImageResource, cacheable: Boolean = true): ImageProvider

}