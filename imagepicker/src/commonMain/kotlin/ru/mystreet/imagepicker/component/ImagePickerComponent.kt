package ru.mystreet.imagepicker.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values
import ru.mystreet.imagepicker.domain.entity.SelectedImages
import ru.mystreet.imagepicker.presentation.ImagePickerImageLoadStore
import ru.mystreet.imagepicker.presentation.ImagePickerStore

class ImagePickerComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val loadStore: ImagePickerImageLoadStore,
) : AppComponentContext(componentContext), ImagePicker {

    val store: ImagePickerStore = getStore()

    init {
        val disposable = loadStore.labels(object : Observer<ImagePickerImageLoadStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: ImagePickerImageLoadStore.Label) {
                when (value) {
                    ImagePickerImageLoadStore.Label.Loaded -> onBack()
                }
            }

        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    private val state = store.values(this)

    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }

    override val images: Value<SelectedImages> = state.map { it.selectedImages }

    override fun onLoad() {
        loadStore.accept(ImagePickerImageLoadStore.Intent.Load(store.state.selectedImages.images.map { it.content }))
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onPick(images: List<ByteArray>) {
        store.accept(ImagePickerStore.Intent.Pick(images))
    }

    override fun onRemove(index: Int) {
        store.accept(ImagePickerStore.Intent.Remove(index))
    }
}