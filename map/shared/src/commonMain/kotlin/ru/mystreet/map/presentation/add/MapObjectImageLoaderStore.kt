package ru.mystreet.map.presentation.add

import ru.mystreet.imagepicker.presentation.ImagePickerImageLoadStore

interface MapObjectImageLoaderStore : ImagePickerImageLoadStore {

    data class Params(
        val mapObjectId: Long,
    )

}