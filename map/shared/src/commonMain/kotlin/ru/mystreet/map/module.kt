package ru.mystreet.map

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.imagepicker.imagePickerModule
import ru.mystreet.map.data.converter.GraphqlFramedMapObjectsConverter
import ru.mystreet.map.data.converter.GraphqlMapObjectTagsConverter
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.converter.SQLDelightFramedMapObjectConverter
import ru.mystreet.map.data.repository.DataStoreLayersConfigRepositoryImpl
import ru.mystreet.map.data.repository.DataStoreLocalMapConfigRepository
import ru.mystreet.map.data.repository.GraphQLRemoteMapObjectReviewsRepository
import ru.mystreet.map.data.repository.GraphqlMapObjectTagRepository
import ru.mystreet.map.data.repository.GraphqlMapObjectsRepository
import ru.mystreet.map.data.repository.GraphqlRemoteMapObjectsRepository
import ru.mystreet.map.data.repository.SQLDelightLocalFramedMapObjectsRepository
import ru.mystreet.map.data.repository.SQLDelightLocalMapFramesRepository
import ru.mystreet.map.data.service.FramedMapObjectsQueueServiceImpl
import ru.mystreet.map.data.store5.GetSingleLocalFramedMapObjectSOT
import ru.mystreet.map.data.store5.GetSingleRemoteFramedMapObjectsSOT
import ru.mystreet.map.data.store5.SingleFramedSingleMapObjectsStore5Impl
import ru.mystreet.map.data.usecase.GetMapObjectReviewsPagingSourceUseCaseImpl
import ru.mystreet.map.domain.repository.LayersConfigRepository
import ru.mystreet.map.domain.repository.LocalFramedMapObjectsRepository
import ru.mystreet.map.domain.repository.LocalMapConfigRepository
import ru.mystreet.map.domain.repository.LocalMapFramesRepository
import ru.mystreet.map.domain.repository.MapObjectTagRepository
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.mystreet.map.domain.repository.RemoteMapObjectReviewsRepository
import ru.mystreet.map.domain.repository.RemoteMapObjectsRepository
import ru.mystreet.map.domain.service.FramedMapObjectsQueueService
import ru.mystreet.map.domain.service.FramesService
import ru.mystreet.map.domain.store5.SingleFramedMapObjectsStore5
import ru.mystreet.map.domain.usecase.AddLocalFramedMapObjectUseCase
import ru.mystreet.map.domain.usecase.AddMapObjectUseCase
import ru.mystreet.map.domain.usecase.AddRemoteMapObjectUseCase
import ru.mystreet.map.domain.usecase.AddTagToFieldUseCase
import ru.mystreet.map.domain.usecase.CalculateFrameForPointUseCase
import ru.mystreet.map.domain.usecase.CalculateFramesForVisibleAreaUseCase
import ru.mystreet.map.domain.usecase.DeleteLocalMapObjectUseCase
import ru.mystreet.map.domain.usecase.DeleteMapObjectUseCase
import ru.mystreet.map.domain.usecase.DeleteRemoteMapObjectUseCase
import ru.mystreet.map.domain.usecase.EditMapObjectUseCase
import ru.mystreet.map.domain.usecase.FlowLocalFramedMapObjectUseCase
import ru.mystreet.map.domain.usecase.GetFramedMapObjectsUseCase
import ru.mystreet.map.domain.usecase.GetLocalFramedMapObjectUseCase
import ru.mystreet.map.domain.usecase.GetMapObjectReviewsPagingSourceUseCase
import ru.mystreet.map.domain.usecase.GetRemoteFramedMapObjectsUseCase
import ru.mystreet.map.domain.usecase.LoadLocalMapConfigUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectTagsWithTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectUseCase
import ru.mystreet.map.domain.usecase.QueueFramedMapObjectsUseCase
import ru.mystreet.map.domain.usecase.RemoteEditMapObjectUseCase
import ru.mystreet.map.domain.usecase.RemoveTagFromFieldUseCase
import ru.mystreet.map.domain.usecase.SaveLocalFramedMapObjectUseCase
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.domain.usecase.SetMapObjectFavouriteUseCase
import ru.mystreet.map.domain.usecase.UploadMapObjectImagesUseCase
import ru.mystreet.map.map.data.service.FramesServiceImpl

val mapSharedModule = module {
    includes(
        imagePickerModule,
        mapSharedConverterModule,
        mapSharedServiceModule,
        mapSharedStore5Module,
        mapSharedRepositoryModule,
        mapSharedUseCaseModule,
    )
}

private val mapSharedConverterModule = module {
    singleOf(::GraphqlMapObjectsConverter)
    singleOf(::GraphqlFramedMapObjectsConverter)
    singleOf(::GraphqlMapObjectTagsConverter)
    singleOf(::SQLDelightFramedMapObjectConverter)
}

private val mapSharedServiceModule = module {
    singleOf(::FramedMapObjectsQueueServiceImpl) bind FramedMapObjectsQueueService::class
    singleOf(::FramesServiceImpl) bind FramesService::class
}

private val mapSharedStore5Module = module {
    singleOf(::SingleFramedSingleMapObjectsStore5Impl) bind SingleFramedMapObjectsStore5::class
    singleOf(::GetSingleRemoteFramedMapObjectsSOT)
    singleOf(::GetSingleLocalFramedMapObjectSOT)
}

private val mapSharedRepositoryModule = module {
    singleOf(::DataStoreLayersConfigRepositoryImpl) bind LayersConfigRepository::class
    singleOf(::GraphqlRemoteMapObjectsRepository) bind RemoteMapObjectsRepository::class
    singleOf(::SQLDelightLocalMapFramesRepository) bind LocalMapFramesRepository::class
    singleOf(::SQLDelightLocalFramedMapObjectsRepository) bind LocalFramedMapObjectsRepository::class
    singleOf(::GraphqlMapObjectsRepository) bind MapObjectsRepository::class
    singleOf(::DataStoreLocalMapConfigRepository) bind LocalMapConfigRepository::class
    singleOf(::GraphqlMapObjectTagRepository) bind MapObjectTagRepository::class
    singleOf(::GraphQLRemoteMapObjectReviewsRepository) bind RemoteMapObjectReviewsRepository::class
}

private val mapSharedUseCaseModule = module {
    factoryOf(::EditMapObjectUseCase)
    factoryOf(::AddLocalFramedMapObjectUseCase)
    factoryOf(::AddMapObjectUseCase)
    factoryOf(::AddRemoteMapObjectUseCase)
    factoryOf(::RemoteEditMapObjectUseCase)
    factoryOf(::DeleteLocalMapObjectUseCase)
    factoryOf(::DeleteRemoteMapObjectUseCase)
    factoryOf(::DeleteMapObjectUseCase)
    factoryOf(::CalculateFrameForPointUseCase)
    factoryOf(::SetMapObjectFavouriteUseCase)
    factoryOf(::LoadLocalMapConfigUseCase)
    factoryOf(::SaveMapInitialCameraPositionUseCase)
    factoryOf(::CalculateFramesForVisibleAreaUseCase)
    factoryOf(::LoadMapObjectTagsWithTitleUseCase)
    factoryOf(::AddTagToFieldUseCase)
    factoryOf(::RemoveTagFromFieldUseCase)
    factoryOf(::LoadMapObjectUseCase)
    factoryOf(::RemoveTagFromFieldUseCase)
    factoryOf(::UploadMapObjectImagesUseCase)
    factoryOf(::GetFramedMapObjectsUseCase)
    factoryOf(::GetLocalFramedMapObjectUseCase)
    factoryOf(::GetRemoteFramedMapObjectsUseCase)
    factoryOf(::FlowLocalFramedMapObjectUseCase)
    factoryOf(::QueueFramedMapObjectsUseCase)
    factoryOf(::SaveLocalFramedMapObjectUseCase)
    factoryOf(::GetMapObjectReviewsPagingSourceUseCaseImpl) bind GetMapObjectReviewsPagingSourceUseCase::class
}