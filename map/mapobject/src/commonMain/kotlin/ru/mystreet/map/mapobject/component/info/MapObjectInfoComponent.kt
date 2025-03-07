package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnResume
import com.arkivanov.essenty.lifecycle.doOnStop
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.core.component.diChildContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.component.Map
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.presentation.info.MapObjectInfoStore

class MapObjectInfoComponent(
    componentContext: DIComponentContext,
    mapObjectId: Long,
    private val onImagePicker: () -> Unit,
    private val onEdit: () -> Unit,
    private val onAddReview: () -> Unit,
    private val map: Map,
) : AppComponentContext(componentContext), MapObjectInfo {

    init {
        lifecycle.doOnResume {
            map.setSelected(mapObjectId)
        }
        lifecycle.doOnStop {
            map.setSelected(null)
        }
    }

    private val store: MapObjectInfoStore =
        getSavedStateStore(
            key = "object_info",
            initialSavedState = { MapObjectInfoStore.SavedState(mapObjectId) },
        )

    override val reviews: MapObjectReviews =
        MapObjectReviewsComponent(
            diChildContext("map_object_reviews"),
            mapObjectId,
            onAddReview = this::onAddReview,
        )

    override fun onImagePicker() {
        onImagePicker.invoke()
    }

    override fun onFavourite(value: Boolean) {
        store.accept(MapObjectInfoStore.Intent.SetFavourite(value))
    }

    override fun onEdit() {
        onEdit.invoke()
    }


    override fun onAddReview() {
        onAddReview.invoke()
    }

    override fun onRefresh() {
        reviews.onRefresh?.invoke()
        store.accept(MapObjectInfoStore.Intent.Refresh)
    }

    private val state = store.values(this)

    override val isLoading: Value<Boolean> = state.map { it.isLoading }

    override val mapObjectInfo: Value<ValueContainer<MapObject?>> =
        state.map { ValueContainer(it.mapObject) }

}