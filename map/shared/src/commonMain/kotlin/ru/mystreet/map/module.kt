package ru.mystreet.map

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.data.converter.GraphqlMapObjectTagsConverter
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.formatter.RawMapObjectFieldFormatter
import ru.mystreet.map.data.repository.DataStoreLocalMapConfigRepository
import ru.mystreet.map.data.repository.GraphqlMapObjectTagRepository
import ru.mystreet.map.data.repository.GraphqlMapObjectsRepository
import ru.mystreet.map.data.validator.RegexMapObjectFieldValidator
import ru.mystreet.map.domain.formatter.MapObjectFieldFormatter
import ru.mystreet.map.domain.repository.LocalMapConfigRepository
import ru.mystreet.map.domain.repository.MapObjectTagRepository
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.mystreet.map.domain.usecase.AddMapObjectUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateTitleUseCase
import ru.mystreet.map.domain.usecase.FormatTitleUseCase
import ru.mystreet.map.domain.usecase.LoadLocalMapConfigUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectTagsWithTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectUseCase
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.domain.usecase.ValidateTitleUseCase
import ru.mystreet.map.domain.validator.MapObjectFieldValidator
import ru.mystreet.map.presentation.EditMapNewObjectInfoStore
import ru.mystreet.map.presentation.EditMapNewObjectInfoStoreImpl
import ru.mystreet.map.presentation.EditMapNewObjectLoadingStore
import ru.mystreet.map.presentation.EditMapNewObjectLoadingStoreImpl
import ru.mystreet.map.presentation.EditMapSelectCategoryStore
import ru.mystreet.map.presentation.EditMapSelectCategoryStoreImpl
import ru.mystreet.map.presentation.EditMapStore
import ru.mystreet.map.presentation.EditMapStoreImpl
import ru.mystreet.map.presentation.MapObjectInfoStore
import ru.mystreet.map.presentation.MapObjectInfoStoreImpl

val mapSharedModule = module {
    factoryOf(::EditMapStoreImpl) bind EditMapStore::class
    factoryOf(::EditMapSelectCategoryStoreImpl) bind EditMapSelectCategoryStore::class
    factoryOf(::EditMapNewObjectInfoStoreImpl) bind EditMapNewObjectInfoStore::class
    factoryOf(::EditMapNewObjectLoadingStoreImpl) bind EditMapNewObjectLoadingStore::class
    factoryOf(::MapObjectInfoStoreImpl) bind MapObjectInfoStore::class
    factoryOf(::AddMapObjectUseCase)
    factoryOf(::LoadLocalMapConfigUseCase)
    factoryOf(::SaveMapInitialCameraPositionUseCase)
    factoryOf(::FormatAndValidateTitleUseCase)
    factoryOf(::ValidateTitleUseCase)
    factoryOf(::FormatTitleUseCase)
    factoryOf(::LoadMapObjectTagsWithTitleUseCase)
    factoryOf(::LoadMapObjectUseCase)
    factoryOf(::RegexMapObjectFieldValidator) bind MapObjectFieldValidator::class
    factoryOf(::RawMapObjectFieldFormatter) bind MapObjectFieldFormatter::class
    singleOf(::GraphqlMapObjectsConverter)
    singleOf(::GraphqlMapObjectTagsConverter)
    singleOf(::GraphqlMapObjectsRepository) bind MapObjectsRepository::class
    singleOf(::DataStoreLocalMapConfigRepository) bind LocalMapConfigRepository::class
    singleOf(::GraphqlMapObjectTagRepository) bind MapObjectTagRepository::class
}