package ru.mystreet.map.domain.entity

import ru.sulgik.mapkit.map.ClusterizedPlacemarkCollection
import ru.sulgik.mapkit.map.PlacemarkMapObject

class MapCategoryWithPlacemarks(
    val category: MapObjectCategory,
    val clusterizedPlacemark: ClusterizedPlacemarkCollection,
    val placemarks: List<PlacemarkMapObject>,
)