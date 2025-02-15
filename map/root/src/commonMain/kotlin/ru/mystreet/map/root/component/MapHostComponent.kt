package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.core.component.diChildStack
import ru.mystreet.errors.component.ErrorsList
import ru.mystreet.errors.component.ErrorsListComponent
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.editmap.EditMap
import ru.mystreet.map.component.editmap.EditMapComponent
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.general.component.GeneralMapComponent
import ru.mystreet.map.map.component.FramedMapComponent
import ru.mystreet.map.parks.component.ParksMapComponent
import ru.mystreet.map.root.component.external.DefaultMapExternalHostComponent

class MapHostComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
) : AppComponentContext(componentContext), MapHost {

    override val map: Map = FramedMapComponent(
        componentContext = diChildContext(key = "map_kit_component"),
        mapConfig = mapConfig,
        onMapObjectInfo = this::onMapObjectInfo
    )
    override val editMap: EditMap = EditMapComponent(diChildContext("edit_map"), map.mapController)

    override val uiConfig: Value<MapHost.UIConfig> = editMap.isEnabled.map {
        MapHost.UIConfig(!it)
    }
    override val errorsList: ErrorsList = ErrorsListComponent(diChildContext("errors_list"))

    private val navigation = StackNavigation<MapHost.Config>()

    override val childStack = diChildStack(
        source = navigation,
        serializer = MapHost.Config.serializer(),
        initialConfiguration = MapHost.Config.General,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: MapHost.Config,
        componentContext: DIComponentContext,
    ): MapHost.Child {
        return when (config) {
            MapHost.Config.General -> MapHost.Child.General(
                GeneralMapComponent(
                    componentContext = componentContext,
                    editMap = editMap,
                    map = map,
                )
            )

            MapHost.Config.Parks -> MapHost.Child.Parks(ParksMapComponent(componentContext))

            else -> throw IllegalArgumentException("No childStack navigation for $config")
        }
    }

    private fun onMapObjectInfo(id: Long) {
        external.mapObject.onMapObject(id)
    }

    override fun onNavigate(config: MapHost.Config) {
        if (!showExternalScreen(config))
            navigation.bringToFront(config)
    }

    override val external =
        DefaultMapExternalHostComponent(diChildContext("map_external"), map = map)


    private fun showExternalScreen(config: MapHost.Config): Boolean {
        return when (config) {
            MapHost.Config.Account -> {
                external.accountHost.show()
                true
            }

            MapHost.Config.Search -> TODO()
            else -> false
        }
    }

}