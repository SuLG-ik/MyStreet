package ru.mystreet.map.map.component

import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Point

interface MapObjects {

    fun onBind()

    fun onUnbind()

    fun setCategories(categories: List<MapObjectCategory>)

    val userLocationPoint: Point?

}