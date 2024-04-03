package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.Pageable

class CalculateNextPageUseCase {

    operator fun invoke(previousPageable: Pageable, data: List<*>): Pageable? {
        if (data.size < previousPageable.pageSize) {
            return null
        }
        return previousPageable.copy(page = data.size + 1)
    }

}