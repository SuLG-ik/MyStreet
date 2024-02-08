package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.GeneralLayer

interface GeneralMapAppBar {

    val layers: Value<List<GeneralLayer>>

    fun onToggleEnabled(layer: GeneralLayer)

}