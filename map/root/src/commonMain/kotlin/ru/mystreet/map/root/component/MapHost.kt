package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import ru.mystreet.bottomsheet.host.component.SheetHost
import ru.mystreet.errors.component.ErrorsList
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.editmap.EditMap
import ru.mystreet.map.general.component.GeneralMap
import ru.mystreet.map.parks.component.ParksMap
import ru.mystreet.map.trash.component.TrashMap

interface MapHost {

    val childStack: Value<ChildStack<Config, Child>>

    val uiConfig: Value<UIConfig>

    val errorsList: ErrorsList

    data class UIConfig(
        val isBottomBarVisible: Boolean,
    )

    @Serializable
    @Polymorphic
    sealed class Config {

        @Serializable
        data object General : Config()

        @Serializable
        data object Parks : Config()

        @Serializable
        data object Search : Config()

        @Serializable
        data object Account : Config()

    }

    @Serializable
    @Polymorphic
    sealed class Child {

        @Serializable
        data class General(
            val component: GeneralMap,
        ) : Child()

        @Serializable
        data class Parks(
            val component: ParksMap,
        ) : Child()

        @Serializable
        data class Trash(
            val component: TrashMap,
        ) : Child()

    }

    val map: Map

    fun onNavigate(config: Config)

    val sheetHost: SheetHost<*>

    val editMap: EditMap

}