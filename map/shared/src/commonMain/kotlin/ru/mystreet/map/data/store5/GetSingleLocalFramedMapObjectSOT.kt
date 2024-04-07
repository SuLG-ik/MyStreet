package ru.mystreet.map.data.store5

import org.mobilenativefoundation.store.store5.SourceOfTruth
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.usecase.FlowLocalFramedMapObjectUseCase
import ru.mystreet.map.domain.usecase.SaveLocalFramedMapObjectUseCase

class GetSingleLocalFramedMapObjectSOT(
    saveLocalFramedMapObjectUseCase: SaveLocalFramedMapObjectUseCase,
    flowLocalFramedMapObjectUseCase: FlowLocalFramedMapObjectUseCase,
) :
    SourceOfTruth<MapFrame, FramedMapObjects, FramedMapObjects> by SourceOfTruth.of(
        reader = flowLocalFramedMapObjectUseCase::invoke,
        writer = { _, value -> saveLocalFramedMapObjectUseCase(value) },
    )