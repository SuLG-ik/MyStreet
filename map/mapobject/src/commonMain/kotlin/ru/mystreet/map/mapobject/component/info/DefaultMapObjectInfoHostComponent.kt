package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.app.feature.dialogs.component.modal.DefaultModalDialogComponent
import ru.mystreet.app.feature.dialogs.component.modal.ModalDialogComponent
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.RefreshableChild.Companion.refreshActive
import ru.mystreet.core.component.diChildStack
import ru.mystreet.core.component.getStore
import ru.mystreet.imagepicker.component.ImagePickerComponent
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.edit.MapObjectEditComponent
import ru.mystreet.map.mapobject.presentation.add.MapObjectImageLoaderStore

class DefaultMapObjectInfoHostComponent(
    componentContext: DIComponentContext,
    private val map: Map,
) : AppComponentContext(componentContext), MapObjectInfoHostComponent,
    ModalDialogComponent by DefaultModalDialogComponent(componentContext) {

    private val navigation = StackNavigation<Config>()

    private val loadImageStore: MapObjectImageLoaderStore =
        getStore()

    override val childStack: Value<ChildStack<*, MapObjectInfoHostComponent.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.Loading,
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): MapObjectInfoHostComponent.Child {
        return when (config) {
            is Config.Info -> MapObjectInfoHostComponent.Child.Info(
                MapObjectInfoComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                    onEdit = { onEdit(config.id) },
                    onImagePicker = { onImagePicker(config.id) },
                    onAddReview = { onAddReview(config.id) },
                    map = map,
                )
            )

            is Config.ImagePicker -> MapObjectInfoHostComponent.Child.AddImage(
                ImagePickerComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    loadStore = loadImageStore,
                )
            )

            is Config.Edit -> MapObjectInfoHostComponent.Child.Edit(
                MapObjectEditComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                    onBack = this::onBack,
                    onCompleted = { onInfo(config.id) },
                    onDelete = this::onBack,
                )
            )

            is Config.AddReview -> MapObjectInfoHostComponent.Child.AddReview(
                MapObjectReviewAddComponent(
                    componentContext = diComponentContext,
                    mapObjectId = config.id,
                    onBack = this::onBack,
                    onCompleted = { onInfo(config.id) }
                )
            )

            Config.Loading -> MapObjectInfoHostComponent.Child.Empty
        }
    }

    private fun onBack() {
        if (childStack.value.backStack.isEmpty()) {
            hide()
        }
        navigation.pop()
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

    override fun onMapObject(id: Long) {
        onInfo(id)
        show()
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Loading : Config

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