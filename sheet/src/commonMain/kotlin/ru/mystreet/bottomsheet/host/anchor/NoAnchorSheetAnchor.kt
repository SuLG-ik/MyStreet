package ru.mystreet.bottomsheet.host.anchor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue

@OptIn(ExperimentalMaterial3Api::class)
internal class NoAnchorSheetAnchor(
    previousAnchor: SheetAnchor? = null,
) : SheetAnchor {

    override val currentValue: SheetValue = previousAnchor?.currentValue ?: SheetValue.Hidden

    override val targetValue: SheetValue = previousAnchor?.targetValue ?: SheetValue.Hidden

    var targetAction: SheetValue? = null

    override fun hide() {
        targetAction = SheetValue.Hidden
    }

    override fun show() {
        targetAction = SheetValue.Expanded
    }


}