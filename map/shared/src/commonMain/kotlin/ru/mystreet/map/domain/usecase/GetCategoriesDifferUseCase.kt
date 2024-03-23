package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObjectCategory

class GetCategoriesDifferUseCase {
    operator fun invoke(
        current: List<MapObjectCategory>?,
        new: List<MapObjectCategory>,
    ): Pair<List<MapObjectCategory>, List<MapObjectCategory>> {
        if (current == null)
            return new to emptyList()
        return new.filter { it in current } to current.filterNot { it in new }
    }
}