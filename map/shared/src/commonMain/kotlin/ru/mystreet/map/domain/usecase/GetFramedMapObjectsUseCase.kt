package ru.mystreet.map.domain.usecase

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.store5.SingleFramedMapObjectsStore5

class GetFramedMapObjectsUseCase(
    private val singleFramedMapObjectsStore5: SingleFramedMapObjectsStore5,
) {

    suspend operator fun invoke(
        frame: MapFrame,
    ): Flow<FramedMapObjects> {
        return coroutineScope {
            singleFramedMapObjectsStore5.stream(
                StoreReadRequest.cached(
                    key = frame,
                    refresh = true,
                )
            ).filterNot { it is StoreReadResponse.Loading || it is StoreReadResponse.NoNewData }
                .map { it.requireData() }
        }
    }

}