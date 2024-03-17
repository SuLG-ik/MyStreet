package ru.mystreet.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithSkipping
import ru.mystreet.imagepicker.presentation.ImagePickerImageLoadStore
import ru.mystreet.map.domain.usecase.UploadMapObjectImagesUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectImageLoaderStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    params: MapObjectImageLoaderStore.Params,
    uploadMapObjectImagesUseCase: UploadMapObjectImagesUseCase,
) : MapObjectImageLoaderStore,
    Store<ImagePickerImageLoadStore.Intent, ImagePickerImageLoadStore.State, ImagePickerImageLoadStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "MapObjectImageLoaderStoreImpl",
        initialState = ImagePickerImageLoadStore.State(isLoading = false),
        reducer = {
            when (it) {
                is Message.SetLoading -> copy(isLoading = it.value)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntentWithSkipping<ImagePickerImageLoadStore.Intent.Load, _, _, _, _, _> {
                dispatch(Message.SetLoading(true))
                deferredLaunch {
                    uploadMapObjectImagesUseCase(params.mapObjectId, it.images)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetLoading(false))
                        publish(ImagePickerImageLoadStore.Label.Loaded)
                    }
                }
            }
        },
    ) {

    sealed interface Message {
        data class SetLoading(val value: Boolean) : Message
    }
}