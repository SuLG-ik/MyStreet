package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.sulgik.mapkit.geometry.Latitude
import ru.sulgik.mapkit.geometry.Longitude

class AddRemoteMapObjectUseCase(
    private val repository: MapObjectsRepository,
) {
    suspend operator fun invoke(field: AddMapObjectField): MapObject {
        return repository.addMapObject(
            title = field.title.value,
            description = field.description.value,
            category = field.category,
            latitude = Latitude(field.point.latitude.value),
            longitude = Longitude(field.point.longitude.value),
            tags = field.tags.tags.tags,
        )
    }
}