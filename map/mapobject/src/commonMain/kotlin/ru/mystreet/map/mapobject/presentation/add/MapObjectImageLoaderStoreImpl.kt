package ru.mystreet.map.mapobject.presentation.add

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.errors.domain.ErrorDispatcher
import ru.mystreet.errors.store.onIntentSafeSkipping
import ru.mystreet.imagepicker.presentation.ImagePickerImageLoadStore
import ru.mystreet.map.domain.usecase.UploadMapObjectImagesUseCase
import ru.mystreet.map.presentation.add.MapObjectImageLoaderStore

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectImageLoaderStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    params: MapObjectImageLoaderStore.Params,
    uploadMapObjectImagesUseCase: UploadMapObjectImagesUseCase,
    errorDispatcher: ErrorDispatcher,
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
            onIntentSafeSkipping(errorDispatcher) { it: ImagePickerImageLoadStore.Intent.Load ->
                dispatch(Message.SetLoading(true))
                launch {
                    try {
                        uploadMapObjectImagesUseCase(params.mapObjectId, it.images)
                        withContext(Dispatchers.Main) {
                            dispatch(Message.SetLoading(false))
                            publish(ImagePickerImageLoadStore.Label.Loaded)
                        }
                    } catch (e:Exception) {
                        withContext(Dispatchers.Main) {
                            publish(ImagePickerImageLoadStore.Label.Cancel)
                        }
                        throw e
                    }
                }
            }
        },
    ) {

    sealed interface Message {
        data class SetLoading(val value: Boolean) : Message
    }
}