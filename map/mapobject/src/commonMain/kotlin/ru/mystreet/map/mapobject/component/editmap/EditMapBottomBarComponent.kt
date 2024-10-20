package ru.mystreet.map.component.editmap

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack
import ru.mystreet.map.component.add.EditMapNewObjectInfoComponent
import ru.mystreet.map.component.add.EditMapNewObjectLoadingComponent
import ru.mystreet.map.component.add.EditMapSelectCategoryComponent
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.sulgik.mapkit.geometry.Point

class EditMapBottomBarComponent(
    componentContext: DIComponentContext,
    override val isVisible: Value<Boolean>,
    private val currentTarget: StateFlow<Point>,
    private val onObjectAdded: () -> Unit,
) : AppComponentContext(componentContext), EditMapBottomBar {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, EditMapBottomBar.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.SelectCategory(emptyList()),
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    override fun setCategories(categories: List<MapObjectCategory>) {
        if (categories.size == 1)
            navigation.replaceAll(Config.InfoInput(categories, categories.first(), null))
        else
            navigation.replaceAll(Config.SelectCategory(categories))
    }

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): EditMapBottomBar.Child {
        return when (config) {
            is Config.SelectCategory -> EditMapBottomBar.Child.SelectCategory(
                EditMapSelectCategoryComponent(
                    componentContext = diComponentContext,
                    categories = config.categories,
                    category = config.field?.category,
                    onContinue = { onCategorySelected(config.categories, it, config.field) },
                )
            )

            is Config.InfoInput -> EditMapBottomBar.Child.EditInfo(
                EditMapNewObjectInfoComponent(
                    componentContext = diComponentContext,
                    category = config.category,
                    field = config.field,
                    currentTarget = currentTarget,
                    onContinue = this::onInfoSelected,
                    onBack = { onBackToCategory(config.categories, it) },
                )
            )

            is Config.Loading -> EditMapBottomBar.Child.Loading(
                EditMapNewObjectLoadingComponent(
                    componentContext = diComponentContext,
                    field = config.field,
                    onContinue = this::onObjectAdded,
                )
            )
        }
    }

    private fun onBackToCategory(categories: List<MapObjectCategory>, field: AddMapObjectField) {
        navigation.replaceAll(Config.SelectCategory(categories, field))
    }

    private fun onObjectAdded() {
        onObjectAdded.invoke()
    }


    private fun onCategorySelected(
        categories: List<MapObjectCategory>,
        category: MapObjectCategory,
        field: AddMapObjectField?
    ) {
        navigation.bringToFront(Config.InfoInput(categories, category, field))
    }

    private fun onInfoSelected(field: AddMapObjectField) {
        navigation.bringToFront(Config.Loading(field))
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data class SelectCategory(
            val categories: List<MapObjectCategory>,
            val field: AddMapObjectField? = null,
        ) : Config {
            override fun equals(other: Any?): Boolean {
                return other is SelectCategory && categories == other.categories
            }

            override fun hashCode(): Int {
                return field?.hashCode() ?: 0
            }
        }

        @Serializable
        data class InfoInput(
            val categories: List<MapObjectCategory>,
            val category: MapObjectCategory,
            val field: AddMapObjectField?
        ) :
            Config

        @Serializable
        data class Loading(val field: AddMapObjectField) : Config

    }

}