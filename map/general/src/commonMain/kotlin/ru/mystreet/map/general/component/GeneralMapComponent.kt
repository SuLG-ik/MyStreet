package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.subscribe
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.EditMap

class GeneralMapComponent(
    componentContext: DIComponentContext,
    private val editMap: EditMap,
    private val map: ru.mystreet.map.map.component.Map,
) : AppComponentContext(componentContext), GeneralMap {

    override val appBar: GeneralMapAppBar =
        GeneralMapAppBarComponent(
            componentContext = diChildContext(key = "general_map_app_bar"),
            isInEditMode = editMap.isEnabled,
            onEditModeToggle = editMap::onToggleEnabled,
        )

    init {
        appBar.layers.subscribe(lifecycle) {
            println("aaaaaaaa ${it.joinToString()}")
            map.setCategories(it.filter { it.isEnabled }.map { layer -> layer.type.category })
        }
    }


}

