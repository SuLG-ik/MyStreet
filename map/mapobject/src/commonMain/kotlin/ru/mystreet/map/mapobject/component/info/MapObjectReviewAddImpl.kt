package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.mapobject.presentation.info.MapObjectReviewAddStore

class MapObjectReviewAddComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onCompleted: () -> Unit,
) : AppComponentContext(componentContext), MapObjectReviewAdd {

    private val store: MapObjectReviewAddStore = getSavedStateStore(
        key = "review_add",
        initialSavedState = { MapObjectReviewAddStore.SavedState(title = "", content = "") },
    )

    val state = store.values(this)

    override val field: Value<AddMapObjectReviewField> = state.map { it.field }

    override val isLoading: Value<Boolean> = state.map { it.isLoading }

    override val isContinueAvailable: Value<Boolean> = state.map { it.isLoading }

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

}