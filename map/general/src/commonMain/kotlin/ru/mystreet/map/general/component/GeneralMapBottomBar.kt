package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralSelectedMode

interface GeneralMapBottomBar {

    val selectedMode: Value<GeneralSelectedMode>

    val layers: Value<List<GeneralLayer>>

    fun onGeneralLayerToggle(layer: GeneralLayer)

    fun onModeToggle(mode: GeneralSelectedMode)

}