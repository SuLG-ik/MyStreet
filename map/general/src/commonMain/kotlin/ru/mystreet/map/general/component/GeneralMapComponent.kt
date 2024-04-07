package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.ObserveLifecycleMode
import com.arkivanov.decompose.value.subscribe
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.editmap.EditMap
import ru.mystreet.map.domain.entity.GeneralSelectedMode
import ru.mystreet.map.domain.entity.MapObjectCategory

class GeneralMapComponent(
    componentContext: DIComponentContext,
    private val editMap: EditMap,
    private val map: Map,
) : AppComponentContext(componentContext), GeneralMap {

    override val appBar: GeneralMapAppBar =
        GeneralMapAppBarComponent(
            componentContext = diChildContext(key = "general_map_app_bar"),
            isInEditMode = editMap.isEnabled,
            onEditModeToggle = this::onToggleEnabled,
        )

    override val bottomBar: GeneralMapBottomBar =
        GeneralMapBottomBarComponent(
            componentContext = diChildContext(key = "general_map_bottom_bar")
        )

    private fun onToggleEnabled() {
        editMap.onToggleEnabled(generalMapObjectCategories)
    }

    init {
        bottomBar.layers.subscribe(lifecycle, ObserveLifecycleMode.RESUME_PAUSE) { layers ->
            if (bottomBar.selectedMode.value == GeneralSelectedMode.GENERAL) {
                map.setCategories(layers.filter { it.isEnabled }.map { layer -> layer.type.category })
            }
        }
        bottomBar.selectedMode.subscribe(lifecycle, ObserveLifecycleMode.RESUME_PAUSE) { mode ->
            when (mode) {
                GeneralSelectedMode.GENERAL ->
                    map.setCategories(bottomBar.layers.value.filter { it.isEnabled }
                        .map { layer -> layer.type.category })
                GeneralSelectedMode.STREETLIGHT -> map.setCategories(listOf(MapObjectCategory.StreetLight))
                GeneralSelectedMode.TRASH -> map.setCategories(listOf(MapObjectCategory.Trash))
            }
        }
    }

    companion object {
        val generalMapObjectCategories = listOf(
            MapObjectCategory.Bench,
            MapObjectCategory.Playground,
            MapObjectCategory.StreetLight,
            MapObjectCategory.Monument,
            MapObjectCategory.Installation,
            MapObjectCategory.Fountain,
            MapObjectCategory.Bower,
            MapObjectCategory.GreenArea,
            MapObjectCategory.PublicWC,
            MapObjectCategory.Other,
            MapObjectCategory.Trash,
        )

    }


}

