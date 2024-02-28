package ru.mystreet.imagepicker.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.imagepicker.domain.entity.ImageItem

interface ImagePicker {


    val isContinueAvailable: Value<Boolean>

    val images: Value<List<ImageItem>>

    fun onLoad()

    fun onBack()

    fun onPick(images: List<ByteArray>)

    fun onRemove(index: Int)

}