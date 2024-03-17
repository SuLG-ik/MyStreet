package ru.mystreet.map.map.data.store5

import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.store5.SingleFramedMapObjectsStore5
import ru.mystreet.map.map.domain.store5.GetSingleLocalFramedMapObjectSOT
import ru.mystreet.map.map.domain.store5.GetSingleRemoteFramedMapObjectsSOT
import kotlin.time.Duration.Companion.minutes

class SingleFramedSingleMapObjectsStore5Impl(
    getSingleRemoteFramedMapObjectsSOT: GetSingleRemoteFramedMapObjectsSOT,
    getSingleLocalFramedMapObjectSOT: GetSingleLocalFramedMapObjectSOT,
) : SingleFramedMapObjectsStore5, Store<MapFrame, FramedMapObjects> by StoreBuilder.from(
    fetcher = getSingleRemoteFramedMapObjectsSOT,
    sourceOfTruth = getSingleLocalFramedMapObjectSOT,
).cachePolicy(
    MemoryPolicy.builder<MapFrame, FramedMapObjects>()
        .setExpireAfterWrite(15.minutes)
        .setMaxSize(15)
        .build()
)
    .build()
