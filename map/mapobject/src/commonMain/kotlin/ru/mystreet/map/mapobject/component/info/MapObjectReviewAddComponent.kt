package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.mapobject.presentation.info.MapObjectReviewAddStore

class MapObjectReviewAddComponent(
    componentContext: DIComponentContext,
    mapObjectId: Long,
    private val onBack: () -> Unit,
    private val onCompleted: () -> Unit,
) : AppComponentContext(componentContext), MapObjectReviewAdd {

    private val store: MapObjectReviewAddStore = getSavedStateStore(
        key = "review_add",
        initialSavedState = { MapObjectReviewAddStore.SavedState(mapObjectId = mapObjectId) },
    )

    init {
        val disposable = store.labels(object : Observer<MapObjectReviewAddStore.Label> {
            override fun onComplete() {}

            override fun onNext(value: MapObjectReviewAddStore.Label) {
                when (value) {
                    MapObjectReviewAddStore.Label.Complete -> onCompleted()
                }
            }

        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    val state = store.values(this)

    override val field: Value<AddMapObjectReviewField> = state.map { it.field }

    override val isLoading: Value<Boolean> = state.map { it.isLoading }

    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }

    override fun onContinue() {
        store.accept(MapObjectReviewAddStore.Intent.Continue)
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onTitleInput(value: String) {
        store.accept(MapObjectReviewAddStore.Intent.TitleInput(value))
    }

    override fun onContentInput(value: String) {
        store.accept(MapObjectReviewAddStore.Intent.ContentInput(value))
    }

    override fun onRatingInput(value: Int) {
        store.accept(MapObjectReviewAddStore.Intent.RatingInput(value))
    }

    override fun onAuthorHiddenInput(value: Boolean) {
        store.accept(MapObjectReviewAddStore.Intent.AuthorHiddenInput(value))
    }


}