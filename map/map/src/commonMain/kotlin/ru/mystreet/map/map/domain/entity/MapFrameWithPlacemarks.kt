package ru.mystreet.map.map.domain.entity

import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.Placemark
import ru.mystreet.map.domain.entity.MapObjectCategory

class MapCategoryWithPlacemarks(
    val category: MapObjectCategory,
    val clusterizedPlacemark: ClusterizedPlacemark,
    val placemarks: List<Placemark>,
)