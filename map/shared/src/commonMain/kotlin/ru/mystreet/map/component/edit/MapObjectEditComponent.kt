package ru.mystreet.map.component.edit

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.presentation.edit.MapObjectEditStore

class MapObjectEditComponent(
    componentContext: DIComponentContext,
    mapObjectId: Long,
    private val onBack: () -> Unit,
    private val onCompleted: () -> Unit,
    private val onDelete: () -> Unit,
) : AppComponentContext(componentContext), MapObjectEdit {

    val store: MapObjectEditStore =
        getSavedStateStore(
            key = "map_object_edit",
            initialSavedState = {
                MapObjectEditStore.SavedState(
                    id = mapObjectId,
                    input = null,
                )
            },
        )

    init {
        val disposable = store.labels(object : Observer<MapObjectEditStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: MapObjectEditStore.Label) {
                when (value) {
                    MapObjectEditStore.Label.OnDeleted -> onDelete.invoke()
                    MapObjectEditStore.Label.OnSaved -> onCompleted.invoke()
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }


    private val state = store.values(this)
    override val isLoading: Value<Boolean> = state.map { it.isLoading }
    override val field: Value<ValueContainer<EditMapObjectField?>> =
        state.map { ValueContainer(it.field) }

    override fun onTitleInput(value: String) {
        store.accept(MapObjectEditStore.Intent.TitleInput(value))
    }

    override fun onDescriptionInput(value: String) {
        store.accept(MapObjectEditStore.Intent.DescriptionInput(value))
    }

    override fun onTagInput(value: String) {
        store.accept(MapObjectEditStore.Intent.TagInput(value))
    }

    override fun onTagRemove(value: String) {
        store.accept(MapObjectEditStore.Intent.RemoveTag(value))
    }

    override fun onTagAdd() {
        store.accept(MapObjectEditStore.Intent.AddTag)
    }

    override fun onContinue() {
        store.accept(MapObjectEditStore.Intent.Continue)
    }

    override fun onDelete() {
        store.accept(MapObjectEditStore.Intent.Delete)
    }

    override fun onBack() {
        onBack.invoke()
    }

}