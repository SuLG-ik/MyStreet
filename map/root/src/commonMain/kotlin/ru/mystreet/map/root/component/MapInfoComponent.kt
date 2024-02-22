package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildSlot
import ru.mystreet.core.component.savedValue
import ru.mystreet.map.component.MapObjectInfoComponent

class MapInfoComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), MapInfo {

    private val navigation = SlotNavigation<Config>()
    override val isExpanded: MutableValue<Boolean> = savedValue("is_expanded") { false }

    override val childSlot: Value<ChildSlot<*, MapInfo.Child>> = diChildSlot(
        source = navigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = this::createChild,
    )

    override fun onShowMapObjectInfo(id: Long) {
        navigation.activate(Config.MapObjectInfo(id), onComplete = { isExpanded.value = true })
    }

    override fun onDismiss() {
        isExpanded.value = false
    }

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): MapInfo.Child {
        return when (config) {
            is Config.MapObjectInfo -> MapInfo.Child.MapObjectInfo(
                MapObjectInfoComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                )
            )
        }
    }


    @Serializable
    sealed interface Config {
        @Serializable
        data class MapObjectInfo(val id: Long) : Config
    }

}