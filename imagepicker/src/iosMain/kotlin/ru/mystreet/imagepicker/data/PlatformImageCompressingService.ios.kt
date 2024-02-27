package ru.mystreet.imagepicker.data

import ru.mystreet.imagepicker.domain.service.ImageCompressingService

actual class PlatformImageCompressingService : ImageCompressingService {
    override suspend fun compress(image: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }
}