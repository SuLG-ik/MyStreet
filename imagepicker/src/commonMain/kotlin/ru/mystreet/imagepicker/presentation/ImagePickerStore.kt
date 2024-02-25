package ru.mystreet.imagepicker.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.imagepicker.domain.entity.ImageItem

interface ImagePickerStore :
    Store<ImagePickerStore.Intent, ImagePickerStore.State, ImagePickerStore.Label> {

    sealed interface Intent {
        class Pick(val images: List<ByteArray>) : Intent
        class Remove(val index: Int) : Intent
    }

    data class State(
        val images: List<ImageItem>,
    )

    sealed interface Label

}