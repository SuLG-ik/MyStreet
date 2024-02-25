package ru.mystreet.imagepicker

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.imagepicker.data.PlatformImageCompressingService
import ru.mystreet.imagepicker.domain.service.ImageCompressingService
import ru.mystreet.imagepicker.domain.usecase.CompressImageUseCase
import ru.mystreet.imagepicker.presentation.ImagePickerStore
import ru.mystreet.imagepicker.presentation.ImagePickerStoreImpl

val imagePickerModule = module {
    factoryOf(::ImagePickerStoreImpl) bind ImagePickerStore::class
    singleOf(::PlatformImageCompressingService) bind ImageCompressingService::class
    factoryOf(::CompressImageUseCase)
}