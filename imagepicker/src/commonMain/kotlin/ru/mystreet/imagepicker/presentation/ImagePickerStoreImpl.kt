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
import ru.mystreet.imagepicker.domain.usecase.CompressImageUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class ImagePickerStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    compressImageUseCase: CompressImageUseCase,
) : ImagePickerStore,
    Store<ImagePickerStore.Intent, ImagePickerStore.State, ImagePickerStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "ImagePickerStoreImpl",
        initialState = ImagePickerStore.State(isContinueAvailable = false, images = emptyList()),
        reducer = {
            when (it) {
                is Message.SetImages -> copy(
                    images = it.images,
                    isContinueAvailable = it.images.isNotEmpty(),
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            val id = atomic(0)
            onIntent<ImagePickerStore.Intent.Pick> {
                launch(Dispatchers.Default) {
                    val compressedImages = it.images.map {
                        ImageItem(
                            uniqueId = id.getAndIncrement(),
                            content = compressImageUseCase(it),
                        )
                    }
                    withContext(Dispatchers.Main) {
                        val newImages = state().images.toMutableList()
                        newImages.addAll(compressedImages)
                        dispatch(Message.SetImages(newImages))
                    }
                }
            }
            onIntent<ImagePickerStore.Intent.Remove> {
                val newImages = state().images.toMutableList()
                newImages.removeAt(it.index)
                dispatch(Message.SetImages(newImages))
            }
        },
    ) {
    sealed interface Message {
        data class SetImages(val images: List<ImageItem>) : Message
    }
}