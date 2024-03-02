package ru.mystreet.map.map.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.mobilenativefoundation.store.store5.impl.extensions.get
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.store5.SingleFramedMapObjectsStore5

class GetFramedMapObjectsUseCase(
    private val singleFramedMapObjectsStore5: SingleFramedMapObjectsStore5,
) {

    suspend operator fun invoke(
        frames: List<MapFrame>,
    ): List<FramedMapObjects> {
        return coroutineScope {
            frames.map {
                async { singleFramedMapObjectsStore5.get(it) }
            }.awaitAll()
        }
    }

}