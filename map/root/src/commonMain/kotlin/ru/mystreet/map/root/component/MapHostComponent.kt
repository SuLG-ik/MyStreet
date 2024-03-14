package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.account.component.AccountHost
import ru.mystreet.account.component.AccountHostComponent
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.core.component.diChildStack
import ru.mystreet.map.component.EditMap
import ru.mystreet.map.component.EditMapComponent
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.general.component.GeneralMapComponent
import ru.mystreet.map.map.component.FramedMapComponent
import ru.mystreet.map.map.component.Map
import ru.mystreet.map.parks.component.ParksMapComponent
import ru.mystreet.map.trash.component.TrashMapComponent

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

    override val mapInfo: MapInfo = MapInfoComponent(diChildContext("map_info"))
    override val account: AccountHost = AccountHostComponent(diChildContext("account_host"))

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
            MapHost.Config.Trash -> MapHost.Child.Trash(TrashMapComponent(componentContext))
            else -> throw IllegalArgumentException("No childStack navigation for $config")
        }
    }

    private fun onMapObjectInfo(id: Long) {
        mapInfo.onShowMapObjectInfo(id)
    }

    override fun onNavigate(config: MapHost.Config) {
        if (!showExternalScreen(config))
            navigation.bringToFront(config)
    }

    private fun showExternalScreen(config: MapHost.Config): Boolean {
        return when (config) {
            MapHost.Config.Account -> {
                account.onExpand()
                true
            }

            MapHost.Config.Search -> TODO()
            else -> false
        }
    }

}