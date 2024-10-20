package ru.mystreet.map.component.add

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.LatitudeConfig
import ru.mystreet.map.domain.entity.LongitudeConfig
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.PointConfig
import ru.mystreet.map.presentation.add.EditMapNewObjectInfoStore
import ru.sulgik.mapkit.geometry.Point

class EditMapNewObjectInfoComponent(
    componentContext: DIComponentContext,
    category: MapObjectCategory,
    field: AddMapObjectField?,
    currentTarget: StateFlow<Point>,
    private val onContinue: (AddMapObjectField) -> Unit,
    private val onBack: (AddMapObjectField) -> Unit,
) : AppComponentContext(componentContext), EditMapNewObjectInfo {

    private val store: EditMapNewObjectInfoStore = getSavedStateStore(
        key = "edit_map_object_info",
        initialSavedState = {
            val point = currentTarget.value
            EditMapNewObjectInfoStore.SavedState(
                title = field?.title?.value ?: "",
                description = field?.description?.value ?: "",
                point = PointConfig(
                    latitude = LatitudeConfig(point.latitude.value),
                    longitude = LongitudeConfig(point.longitude.value),
                ),
                category = category,
            )
        }
    )

    private val coroutineScope = coroutineScope()

    override val field: Value<AddMapObjectField> = store.values(this).map { it.field }

    init {
        currentTarget.onEach {
            store.accept(EditMapNewObjectInfoStore.Intent.PointInput(it))
        }.flowOn(Dispatchers.Main).launchIn(coroutineScope)
    }

    override fun onTitleInput(value: String) {
        store.accept(EditMapNewObjectInfoStore.Intent.TitleInput(value))
    }

    override fun onDescriptionInput(value: String) {
        store.accept(EditMapNewObjectInfoStore.Intent.DescriptionInput(value))
    }

    override fun onTagInput(value: String) {
        store.accept(EditMapNewObjectInfoStore.Intent.TagInput(value))
    }

    override fun onTagRemove(value: String) {
        store.accept(EditMapNewObjectInfoStore.Intent.RemoveTag(value))
    }


    override fun onTagAdd() {
        store.accept(EditMapNewObjectInfoStore.Intent.AddTag)
    }

    override fun onContinue() {
        onContinue.invoke(field.value)
    }

    override fun onBack() {
        onBack.invoke(field.value)
    }


}