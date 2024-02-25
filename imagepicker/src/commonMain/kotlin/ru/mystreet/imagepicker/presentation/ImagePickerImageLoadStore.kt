package ru.mystreet.imagepicker.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface ImagePickerImageLoadStore :
    Store<ImagePickerImageLoadStore.Intent, ImagePickerImageLoadStore.State, ImagePickerImageLoadStore.Label> {

    sealed interface Intent {
        class Load(val images: List<ByteArray>) : Intent
    }

    data class State(
        val isLoading: Boolean,
    )

    sealed interface Label {
        data object Loaded: Label
    }

}