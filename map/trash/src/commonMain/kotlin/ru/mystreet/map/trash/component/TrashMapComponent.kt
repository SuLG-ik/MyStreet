package ru.mystreet.map.trash.component

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext

class TrashMapComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), TrashMap {

    override val appBar: TrashMapAppBar =
        TrashMapAppBarComponent(diChildContext("general_map_app_bar"))

}

