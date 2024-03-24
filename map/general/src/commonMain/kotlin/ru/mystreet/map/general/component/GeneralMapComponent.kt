package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.ObserveLifecycleMode
import com.arkivanov.decompose.value.subscribe
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.editmap.EditMap
import ru.mystreet.map.domain.entity.MapObjectCategory

class GeneralMapComponent(
    componentContext: DIComponentContext,
    private val editMap: EditMap,
    private val map: ru.mystreet.map.map.component.Map,
) : AppComponentContext(componentContext), GeneralMap {

    override val appBar: GeneralMapAppBar =
        GeneralMapAppBarComponent(
            componentContext = diChildContext(key = "general_map_app_bar"),
            isInEditMode = editMap.isEnabled,
            onEditModeToggle = this::onToggleEnabled,
        )

    private fun onToggleEnabled() {
        editMap.onToggleEnabled(generalMapObjectCategories)
    }

    init {
        appBar.layers.subscribe(lifecycle, ObserveLifecycleMode.RESUME_PAUSE) {
            map.setCategories(it.filter { it.isEnabled }.map { layer -> layer.type.category })
        }
    }

    companion object {
        val generalMapObjectCategories = listOf(
            MapObjectCategory.Bench,
            MapObjectCategory.Playground,
            MapObjectCategory.StreetLight,
            MapObjectCategory.Monument,
            MapObjectCategory.Fountain,
            MapObjectCategory.Bower,
            MapObjectCategory.GreenArea,
            MapObjectCategory.PublicWC
        )

    }


}

