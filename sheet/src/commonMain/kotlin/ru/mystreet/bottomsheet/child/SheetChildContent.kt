package ru.mystreet.bottomsheet.child

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.bottomsheet.child.component.SheetChild

fun interface SheetChildContent<Child: SheetChild> {

    @Composable
    fun Content(child: Child?, modifier: Modifier)

}