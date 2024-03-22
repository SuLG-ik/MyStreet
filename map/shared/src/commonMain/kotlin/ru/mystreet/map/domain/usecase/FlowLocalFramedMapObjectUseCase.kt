package ru.mystreet.map.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.repository.LocalFramedMapObjectsRepository

class FlowLocalFramedMapObjectUseCase(
    private val localFramedMapObjectsRepository: LocalFramedMapObjectsRepository,
) {

    operator fun invoke(frame: MapFrame): Flow<FramedMapObjects> {
        return localFramedMapObjectsRepository.getMapObjectsFlow(frame)
            ?: MutableStateFlow(FramedMapObjects(frame, emptyList()))
    }

}