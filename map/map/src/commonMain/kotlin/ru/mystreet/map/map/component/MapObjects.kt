package ru.mystreet.map.map.component

import ru.mystreet.map.domain.entity.MapObjectCategory

interface MapObjects {

    fun onBind()

    fun onUnbind()

    fun setCategories(categories: List<MapObjectCategory>)

}