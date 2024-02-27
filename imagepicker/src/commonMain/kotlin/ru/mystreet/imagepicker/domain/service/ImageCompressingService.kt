package ru.mystreet.imagepicker.domain.service

interface ImageCompressingService {

    suspend fun compress(image: ByteArray): ByteArray

}