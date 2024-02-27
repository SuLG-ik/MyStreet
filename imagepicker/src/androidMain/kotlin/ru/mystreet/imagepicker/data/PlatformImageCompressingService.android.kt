package ru.mystreet.imagepicker.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.mystreet.imagepicker.domain.service.ImageCompressingService
import java.io.ByteArrayOutputStream

actual class PlatformImageCompressingService : ImageCompressingService {
    override suspend fun compress(image: ByteArray): ByteArray {
        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 1000, 1000, true)
        bitmap.recycle()
        val newImageBytes = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 85, newImageBytes)
        scaledBitmap.recycle()
        return newImageBytes.toByteArray()
    }
}
