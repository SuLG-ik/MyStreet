package ru.mystreet.imagepicker.domain.usecase

import ru.mystreet.imagepicker.domain.service.ImageCompressingService

class CompressImageUseCase(
    private val imageCompressingService: ImageCompressingService,
) {

    suspend operator fun invoke(image: ByteArray): ByteArray {
        return imageCompressingService.compress(image)
    }

}