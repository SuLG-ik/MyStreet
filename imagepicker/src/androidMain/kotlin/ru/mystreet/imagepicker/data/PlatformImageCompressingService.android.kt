package ru.mystreet.imagepicker.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.mystreet.imagepicker.domain.service.ImageCompressingService
import java.io.ByteArrayOutputStream

actual class PlatformImageCompressingService : ImageCompressingService {
    override suspend fun compress(image: ByteArray): ByteArray {
        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val factor = bitmap.width / 1000f
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 1000, (bitmap.height / factor).toInt(), true)
        bitmap.recycle()
        val newImageBytes = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 85, newImageBytes)
        scaledBitmap.recycle()
        return newImageBytes.toByteArray()
    }
}
