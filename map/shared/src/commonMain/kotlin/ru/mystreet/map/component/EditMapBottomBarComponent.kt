package ru.mystreet.map.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.subscribe
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack

class EditMapBottomBarComponent(
    componentContext: DIComponentContext,
    override val isVisible: Value<Boolean>,
) : AppComponentContext(componentContext), EditMapBottomBar {

    init {
        isVisible.subscribe(lifecycle) {
            if (!it)
                resetProgress()
        }
    }

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, EditMapBottomBar.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.SelectCategory,
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext
    ): EditMapBottomBar.Child {
        return when(config) {
            Config.SelectCategory -> EditMapBottomBar.Child.SelectCategory(EditMapSelectCategoryComponent(diComponentContext))
        }
    }

    private fun resetProgress() {

    }

    override fun onContinue() {

    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object SelectCategory: Config

    }

}