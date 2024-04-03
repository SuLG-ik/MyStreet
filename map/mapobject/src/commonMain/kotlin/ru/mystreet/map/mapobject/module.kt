package ru.mystreet.map.mapobject

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.imagepicker.imagePickerModule
import ru.mystreet.map.data.formatter.RawMapObjectFieldFormatter
import ru.mystreet.map.data.validator.RegexMapObjectFieldValidator
import ru.mystreet.map.domain.formatter.MapObjectFieldFormatter
import ru.mystreet.map.domain.usecase.AddTagToFieldUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateDescriptionUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateTitleUseCase
import ru.mystreet.map.domain.usecase.FormatDescriptionUseCase
import ru.mystreet.map.domain.usecase.FormatTitleUseCase
import ru.mystreet.map.domain.usecase.RemoveTagFromFieldUseCase
import ru.mystreet.map.domain.usecase.UploadMapObjectImagesUseCase
import ru.mystreet.map.domain.usecase.ValidateDescriptionUseCase
import ru.mystreet.map.domain.usecase.ValidateTitleUseCase
import ru.mystreet.map.domain.validator.MapObjectFieldValidator
import ru.mystreet.map.mapobject.presentation.info.MapObjectReviewsStore
import ru.mystreet.map.mapobject.presentation.info.MapObjectReviewsStoreImpl
import ru.mystreet.map.presentation.add.EditMapNewObjectInfoStore
import ru.mystreet.map.presentation.add.EditMapNewObjectInfoStoreImpl
import ru.mystreet.map.presentation.add.EditMapNewObjectLoadingStore
import ru.mystreet.map.presentation.add.EditMapNewObjectLoadingStoreImpl
import ru.mystreet.map.presentation.add.EditMapSelectCategoryStore
import ru.mystreet.map.presentation.add.EditMapSelectCategoryStoreImpl
import ru.mystreet.map.presentation.add.MapObjectImageLoaderStore
import ru.mystreet.map.presentation.add.MapObjectImageLoaderStoreImpl
import ru.mystreet.map.presentation.edit.EditMapStore
import ru.mystreet.map.presentation.edit.EditMapStoreImpl
import ru.mystreet.map.presentation.edit.MapObjectEditStore
import ru.mystreet.map.presentation.edit.MapObjectEditStoreImpl
import ru.mystreet.map.presentation.info.MapObjectInfoStore
import ru.mystreet.map.presentation.info.MapObjectInfoStoreImpl

val mapObjectModule = module {
    includes(imagePickerModule)
    factoryOf(::EditMapStoreImpl) bind EditMapStore::class
    factoryOf(::EditMapSelectCategoryStoreImpl) bind EditMapSelectCategoryStore::class
    factoryOf(::EditMapNewObjectInfoStoreImpl) bind EditMapNewObjectInfoStore::class
    factoryOf(::EditMapNewObjectLoadingStoreImpl) bind EditMapNewObjectLoadingStore::class
    factoryOf(::MapObjectInfoStoreImpl) bind MapObjectInfoStore::class
    factoryOf(::MapObjectImageLoaderStoreImpl) bind MapObjectImageLoaderStore::class
    factoryOf(::FormatAndValidateTitleUseCase)
    factoryOf(::ValidateTitleUseCase)
    factoryOf(::FormatTitleUseCase)
    factoryOf(::ValidateDescriptionUseCase)
    factoryOf(::FormatDescriptionUseCase)
    factoryOf(::FormatAndValidateDescriptionUseCase)
    factoryOf(::UploadMapObjectImagesUseCase)
    factoryOf(::RegexMapObjectFieldValidator) bind MapObjectFieldValidator::class
    factoryOf(::RawMapObjectFieldFormatter) bind MapObjectFieldFormatter::class
    factoryOf(::MapObjectEditStoreImpl) bind MapObjectEditStore::class
    factoryOf(::MapObjectReviewsStoreImpl) bind MapObjectReviewsStore::class
}