package ru.mystreet.map.general.component

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.EditMap

class GeneralMapComponent(
    componentContext: DIComponentContext,
    private val editMap: EditMap
) : AppComponentContext(componentContext), GeneralMap {

    override val appBar: GeneralMapAppBar =
        GeneralMapAppBarComponent(
            componentContext = diChildContext(key = "general_map_app_bar"),
            isInEditMode = editMap.isEnabled,
            onEditModeToggle = editMap::onToggleEnabled,
        )

}

