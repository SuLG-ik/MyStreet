package ru.mystreet.map.data.store5

import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.store5.GetSingleLocalFramedMapObjectSOT
import ru.mystreet.map.domain.store5.GetSingleRemoteFramedMapObjectsSOT
import ru.mystreet.map.domain.store5.SingleFramedMapObjectsStore5

class SingleFramedSingleMapObjectsStore5Impl(
    getSingleRemoteFramedMapObjectsSOT: GetSingleRemoteFramedMapObjectsSOT,
    getSingleLocalFramedMapObjectSOT: GetSingleLocalFramedMapObjectSOT,
) : SingleFramedMapObjectsStore5,
    Store<MapFrame, FramedMapObjects> by StoreBuilder.from(
        fetcher = getSingleRemoteFramedMapObjectsSOT,
        sourceOfTruth = getSingleLocalFramedMapObjectSOT,
    ).disableCache()
        .build()
