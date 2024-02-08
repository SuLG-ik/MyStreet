package ru.mystreet.map.parks.component

import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext

class ParksMapComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), ParksMap {

    override val appBar: ParksMapAppBar =
        ParksMapAppBarComponent(diChildContext("parks_map_app_bar"))

}

