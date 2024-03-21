package ru.mystreet.bottomsheet.host.anchor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue

@OptIn(ExperimentalMaterial3Api::class)
internal class MergingSheetAnchor : SheetAnchor {

    private var anchor: SheetAnchor = NoAnchorSheetAnchor()

    fun setAnchor(sheetAnchor: SheetAnchor) {
        val previousAnchor = anchor
        anchor = sheetAnchor
        mergeAnchorStates(previousAnchor, sheetAnchor)
    }

    private fun mergeAnchorStates(previousAnchor: SheetAnchor?, newAnchor: SheetAnchor) {
        if (previousAnchor is NoAnchorSheetAnchor) {
            when (previousAnchor.targetAction) {
                SheetValue.Hidden -> newAnchor.hide()
                SheetValue.Expanded -> newAnchor.show()
                SheetValue.PartiallyExpanded -> {}
                null -> {}
            }
        }
    }

    fun clearAnchor() {
        anchor = NoAnchorSheetAnchor(anchor)
    }

    override val currentValue: SheetValue get() = anchor.currentValue

    override val targetValue: SheetValue get() = anchor.targetValue

    override fun hide() {
        anchor.hide()
    }

    override fun show() {
        anchor.show()
    }


}