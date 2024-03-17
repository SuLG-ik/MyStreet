package ru.mystreet.map.image

import android.content.Context
import com.yandex.runtime.image.ImageProvider
import dev.icerock.moko.resources.ImageResource

actual class ImageProvider(
    private val context: Context,
    private val imageResource: ImageResource,
    private val cacheable: Boolean,
) {

    fun toNative(): ImageProvider {
        return ImageProvider.fromResource(context, imageResource.drawableResId, cacheable)
    }

}