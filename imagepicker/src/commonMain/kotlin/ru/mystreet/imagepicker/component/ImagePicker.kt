package ru.mystreet.imagepicker.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.imagepicker.domain.entity.SelectedImages

interface ImagePicker {


    val isContinueAvailable: Value<Boolean>

    val images: Value<SelectedImages>

    fun onLoad()

    fun onBack()

    fun onPick(images: List<ByteArray>)

    fun onRemove(index: Int)

}