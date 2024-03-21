package ru.mystreet.bottomsheet

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

class SheetConfig(
    val id: String,
    val expand: Expand = Expand.MATCH,
    val hasDragHandle: Boolean = true,
) {
    enum class Expand(
        val modifier: Modifier
    ) {
        FULL(
            modifier = Modifier.fillMaxSize()
        ),
        VISIBLE_TOP(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.85f)
        ),
        MATCH(
            modifier = Modifier.fillMaxWidth()
        )
    }
}