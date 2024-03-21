package ru.mystreet.bottomsheet.host.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.mystreet.bottomsheet.host.anchor.SheetAnchor
import ru.mystreet.bottomsheet.child.component.SheetChild
import ru.mystreet.bottomsheet.child.SheetChildContent

@OptIn(ExperimentalMaterial3Api::class)
interface SheetHost<Child: SheetChild> {

    val currentValue: SheetValue

    val childContent: SheetChildContent<Child>

    val currentChild: Value<ChildSlot<*, Child>>

    fun setAnchor(sheetAnchor: SheetAnchor)

    fun clearAnchor()

    fun confirmValueChange(state: SheetValue): Boolean

    fun show()
}