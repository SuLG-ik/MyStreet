package ru.mystreet.imagepicker.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.imagepicker.domain.entity.ImageItem
import ru.mystreet.imagepicker.domain.entity.SelectedImages
import ru.mystreet.imagepicker.domain.usecase.CompressImageUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class ImagePickerStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    compressImageUseCase: CompressImageUseCase,
) : ImagePickerStore,
    Store<ImagePickerStore.Intent, ImagePickerStore.State, ImagePickerStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "ImagePickerStoreImpl",
        initialState = ImagePickerStore.State(
            isContinueAvailable = false,
            selectedImages = SelectedImages(MAX_IMAGES)
        ),
        reducer = {
            when (it) {
                is Message.SetImages -> copy(
                    selectedImages = selectedImages.copy(
                        count = it.images.size,
                        images = it.images,
                    ),
                    isContinueAvailable = it.images.isNotEmpty(),
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            val id = atomic(0)
            onIntent<ImagePickerStore.Intent.Pick> { intent ->
                val currentCount = state().selectedImages.count
                val maxCount = state().selectedImages.maxCount
                val availableImages = maxOf(0, (maxCount - currentCount))
                if (availableImages == 0)
                    return@onIntent
                val images = if (intent.images.size > availableImages)
                    intent.images.slice(0 until availableImages)
                else
                    intent.images
                launch(Dispatchers.Default) {
                    val compressedImages = images.map { image ->
                        ImageItem(
                            uniqueId = id.getAndIncrement(),
                            content = compressImageUseCase(image),
                        )
                    }
                    withContext(Dispatchers.Main) {
                        val newImages = state().selectedImages.images.toMutableList()
                        newImages.addAll(compressedImages)
                        dispatch(Message.SetImages(newImages))
                    }
                }
            }
            onIntent<ImagePickerStore.Intent.Remove> {
                val newImages = state().selectedImages.images.toMutableList()
                newImages.removeAt(it.index)
                dispatch(Message.SetImages(newImages))
            }
        },
    ) {
    sealed interface Message {
        data class SetImages(val images: List<ImageItem>) : Message
    }

    companion object {
        const val MAX_IMAGES = 5
    }
}