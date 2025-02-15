package ru.mystreet.imagepicker.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface ImagePickerImageLoadStore :
    Store<ImagePickerImageLoadStore.Intent, ImagePickerImageLoadStore.State, ImagePickerImageLoadStore.Label> {

    sealed interface Intent {
        data class SetMapObjectId(val id: Long? = null): Intent
        class Load(val images: List<ByteArray>) : Intent
    }

    data class State(
        val mapObjectId: Long? = null,
        val isLoading: Boolean,
    )

    sealed interface Label {
        data object Loaded: Label
        data object Cancel: Label
    }

}