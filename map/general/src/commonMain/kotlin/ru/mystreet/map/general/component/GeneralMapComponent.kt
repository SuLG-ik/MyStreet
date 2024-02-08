package ru.mystreet.map.general.component

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext

class GeneralMapComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), GeneralMap {

    override val appBar: GeneralMapAppBar =
        GeneralMapAppBarComponent(diChildContext("general_map_app_bar"))

}

