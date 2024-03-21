package ru.mystreet.bottomsheet.host.anchor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
internal class StateSheetAnchor(
    private val state: SheetState,
    private val coroutineScope: CoroutineScope,
) : SheetAnchor {

    override val currentValue = state.currentValue

    override val targetValue = state.currentValue

    override fun hide() {
        coroutineScope.launch {
            state.hide()
        }
    }

    override fun show() {
        coroutineScope.launch {
            state.expand()
        }
    }

}