package ru.mystreet.map.map.domain.store5

import org.mobilenativefoundation.store.store5.SourceOfTruth
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.usecase.GetLocalFramedMapObjectUseCase
import ru.mystreet.map.map.domain.usecase.SaveLocalFramedMapObjectUseCase

class GetSingleLocalFramedMapObjectSOT(
    localFramedMapObjectUseCase: GetLocalFramedMapObjectUseCase,
    saveLocalFramedMapObjectUseCase: SaveLocalFramedMapObjectUseCase,
) :
    SourceOfTruth<MapFrame, FramedMapObjects, FramedMapObjects> by SourceOfTruth.of(
        nonFlowReader = localFramedMapObjectUseCase::invoke,
        writer = { _, value -> saveLocalFramedMapObjectUseCase.invoke(value) }
    )