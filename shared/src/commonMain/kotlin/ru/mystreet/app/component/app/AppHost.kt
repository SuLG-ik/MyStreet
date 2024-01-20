package ru.mystreet.app.component.app

import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.component.map.Map
import ru.mystreet.app.context.UIComponentContext
import ru.mystreet.app.resources.IconPack
import ru.mystreet.app.resources.iconpack.Home
import ru.mystreet.app.resources.iconpack.Parks
import ru.mystreet.app.resources.iconpack.Search
import ru.mystreet.app.resources.iconpack.Trash

interface AppHost : UIComponentContext {

    val state: State

    val childStack: Value<ChildStack<Config, ComponentContext>>

    data class State(
        val map: Map,
    ) {
        data class Map(
            val isMapShown: Boolean,
        )
    }


    @Serializable
    @Polymorphic
    sealed class Config {

        abstract val icon: ImageVector

        @Serializable
        object General : Config() {
            override val icon: ImageVector = IconPack.Home
        }

        @Serializable
        object Parks : Config() {
            override val icon: ImageVector = IconPack.Parks
        }

        @Serializable
        object Trash : Config() {
            override val icon: ImageVector = IconPack.Trash
        }

        @Serializable
        object Search : Config() {
            override val icon: ImageVector = IconPack.Search
        }

        companion object {
            val allConfig: List<Config> by lazy { listOf(General, Parks, Trash, Search) }
        }

    }

    val map: Map
    fun onNavigate(config: Config)
}