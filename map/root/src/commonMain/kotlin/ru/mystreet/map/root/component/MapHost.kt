package ru.mystreet.map.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import ru.mystreet.map.general.component.GeneralMap
import ru.mystreet.map.map.component.Map
import ru.mystreet.map.parks.component.ParksMap
import ru.mystreet.map.trash.component.TrashMap

interface MapHost {

    val childStack: Value<ChildStack<Config, Child>>

    @Serializable
    @Polymorphic
    sealed class Config {

        @Serializable
        data object General : Config()

        @Serializable
        data object Parks : Config()

        @Serializable
        data object Trash : Config()

        @Serializable
        data object Search : Config()

        companion object {
            val allConfig: List<Config> by lazy { listOf(General, Parks, Trash, Search) }
        }

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
            val component: ParksMap
        ) : Child()

        @Serializable
        data class Trash(
            val component: TrashMap
        ) : Child()

        @Serializable
        data object Search : Child()


    }

    val map: Map

    fun onNavigate(config: Config)

}