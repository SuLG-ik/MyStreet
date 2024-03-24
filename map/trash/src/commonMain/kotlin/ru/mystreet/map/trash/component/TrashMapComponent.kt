package ru.mystreet.map.trash.component

import com.arkivanov.essenty.lifecycle.doOnResume
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.editmap.EditMap
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.map.component.Map

class TrashMapComponent(
    componentContext: DIComponentContext,
    private val editMap: EditMap,
    private val map: Map,
) : AppComponentContext(componentContext), TrashMap {

    override val appBar: TrashMapAppBar =
        TrashMapAppBarComponent(
            diChildContext("general_map_app_bar"),
            isInEditMode = editMap.isEnabled,
            onEditModeToggle = this::onToggleEnabled
        )

    private fun onToggleEnabled() {
        editMap.onToggleEnabled(listOf(MapObjectCategory.Trash))
    }

    init {
        lifecycle.doOnResume { map.setCategories(listOf(MapObjectCategory.Trash)) }
    }

}

