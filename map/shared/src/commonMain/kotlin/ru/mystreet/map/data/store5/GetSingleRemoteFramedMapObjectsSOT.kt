package ru.mystreet.map.data.store5

import org.mobilenativefoundation.store.store5.Fetcher
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.usecase.GetRemoteFramedMapObjectsUseCase

class GetSingleRemoteFramedMapObjectsSOT(
    getRemoteFramedMapObjectsUseCase: GetRemoteFramedMapObjectsUseCase,
) : Fetcher<MapFrame, FramedMapObjects> by Fetcher.of(
    name = "GetSingleRemoteFramedMapObjectsSOT",
    fetch = getRemoteFramedMapObjectsUseCase::invoke,
)