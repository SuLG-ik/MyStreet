package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.RefreshableChild.Companion.refreshActive
import ru.mystreet.core.component.diChildStack
import ru.mystreet.core.component.getStore
import ru.mystreet.imagepicker.component.ImagePickerComponent
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.edit.MapObjectEditComponent
import ru.mystreet.map.presentation.add.MapObjectImageLoaderStore

class MapObjectInfoHostComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    mapObjectId: Long,
    private val map: Map,
) : AppComponentContext(componentContext), MapObjectInfoHost {

    private val navigation = StackNavigation<Config>()

    private val loadImageStore: MapObjectImageLoaderStore =
        getStore(MapObjectImageLoaderStore.Params(mapObjectId))

    override val childStack: Value<ChildStack<*, MapObjectInfoHost.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.Info(mapObjectId),
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): MapObjectInfoHost.Child {
        return when (config) {
            is Config.Info -> MapObjectInfoHost.Child.Info(
                MapObjectInfoComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                    onEdit = { onEdit(config.id) },
                    onImagePicker = { onImagePicker(config.id) },
                    map = map,
                )
            )

            is Config.ImagePicker -> MapObjectInfoHost.Child.AddImage(
                ImagePickerComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    loadStore = loadImageStore,
                )
            )

            is Config.Edit -> MapObjectInfoHost.Child.Edit(
                MapObjectEditComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                    onBack = this::onBack,
                    onCompleted = { onInfo(config.id) },
                    onDelete = onBack,
                )
            )

            is Config.AddReview -> MapObjectInfoHost.Child.AddReview(
                MapObjectReviewAddComponent(
                    componentContext = diComponentContext,
                    onBack = onBack,
                    onCompleted = { onInfo(config.id) }
                )
            )
        }
    }

    private fun onBack() {
        navigation.pop { if (!it) onBack.invoke() }
    }

    private fun onEdit(id: Long) {
        navigation.bringToFront(Config.Edit(id))
    }

    private fun onInfo(id: Long) {
        navigation.bringToFront(Config.Info(id)) { childStack.refreshActive() }
    }

    private fun onImagePicker(id: Long) {
        navigation.bringToFront(Config.ImagePicker(id))
    }


    private fun onAddReview(id: Long) {
        navigation.bringToFront(Config.AddReview(id))
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data class Info(val id: Long) : Config

        @Serializable
        data class Edit(val id: Long) : Config

        @Serializable
        data class ImagePicker(val id: Long) : Config

        @Serializable
        data class AddReview(val id: Long) : Config
    }

}