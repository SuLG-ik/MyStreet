package ru.mystreet.map.map

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.map.data.converter.GraphqlFramedMapObjectsConverter
import ru.mystreet.map.map.data.converter.SQLDelightFramedMapObjectConverter
import ru.mystreet.map.map.data.repository.GraphqlRemoteMapObjectsRepository
import ru.mystreet.map.map.data.repository.SQLDelightLocalFramedMapObjectsRepository
import ru.mystreet.map.map.data.repository.SQLDelightLocalMapFramesRepository
import ru.mystreet.map.map.data.service.FramedMapObjectsQueueServiceImpl
import ru.mystreet.map.map.data.service.FramesServiceImpl
import ru.mystreet.map.map.data.store5.SingleFramedSingleMapObjectsStore5Impl
import ru.mystreet.map.map.domain.repository.LocalFramedMapObjectsRepository
import ru.mystreet.map.map.domain.repository.LocalMapFramesRepository
import ru.mystreet.map.map.domain.repository.RemoteMapObjectsRepository
import ru.mystreet.map.map.domain.service.FramedMapObjectsQueueService
import ru.mystreet.map.map.domain.service.FramesService
import ru.mystreet.map.map.domain.store5.GetSingleLocalFramedMapObjectSOT
import ru.mystreet.map.map.domain.store5.GetSingleRemoteFramedMapObjectsSOT
import ru.mystreet.map.map.domain.store5.SingleFramedMapObjectsStore5
import ru.mystreet.map.map.domain.usecase.CalculateFramesForVisibleAreaUseCase
import ru.mystreet.map.map.domain.usecase.GetCategoriesDifferUseCase
import ru.mystreet.map.map.domain.usecase.GetFramedMapObjectsUseCase
import ru.mystreet.map.map.domain.usecase.GetLocalFramedMapObjectUseCase
import ru.mystreet.map.map.domain.usecase.GetRemoteFramedMapObjectsUseCase
import ru.mystreet.map.map.domain.usecase.QueueFramedMapObjectsUseCase
import ru.mystreet.map.map.domain.usecase.SaveLocalFramedMapObjectUseCase
import ru.mystreet.map.map.presentation.FramedMapObjectsStore
import ru.mystreet.map.map.presentation.FramedMapObjectsStoreImpl

val mapModule = module {
    factoryOf(::GetCategoriesDifferUseCase)
    factoryOf(::FramedMapObjectsStoreImpl) bind FramedMapObjectsStore::class
    singleOf(::SingleFramedSingleMapObjectsStore5Impl) bind SingleFramedMapObjectsStore5::class
    factoryOf(::GetSingleRemoteFramedMapObjectsSOT)
    factoryOf(::GetSingleLocalFramedMapObjectSOT)
    factoryOf(::GetFramedMapObjectsUseCase)
    factoryOf(::GetLocalFramedMapObjectUseCase)
    factoryOf(::GetRemoteFramedMapObjectsUseCase)
    factoryOf(::QueueFramedMapObjectsUseCase)
    factoryOf(::SaveLocalFramedMapObjectUseCase)
    factoryOf(::FramedMapObjectsQueueServiceImpl) bind FramedMapObjectsQueueService::class
    factoryOf(::CalculateFramesForVisibleAreaUseCase)
    factoryOf(::FramesServiceImpl) bind FramesService::class
    singleOf(::GraphqlRemoteMapObjectsRepository) bind RemoteMapObjectsRepository::class
    singleOf(::GraphqlFramedMapObjectsConverter)
    singleOf(::SQLDelightLocalFramedMapObjectsRepository) bind LocalFramedMapObjectsRepository::class
    singleOf(::SQLDelightLocalMapFramesRepository) bind LocalMapFramesRepository::class
    singleOf(::SQLDelightFramedMapObjectConverter)
}