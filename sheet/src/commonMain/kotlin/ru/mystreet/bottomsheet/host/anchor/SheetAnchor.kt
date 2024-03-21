package ru.mystreet.bottomsheet.host.anchor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue

@OptIn(ExperimentalMaterial3Api::class)
interface SheetAnchor {

    val currentValue: SheetValue

    val targetValue: SheetValue

    fun hide()

    fun show()

}