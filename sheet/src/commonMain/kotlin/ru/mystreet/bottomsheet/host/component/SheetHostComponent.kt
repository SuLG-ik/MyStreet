package ru.mystreet.bottomsheet.host.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.KSerializer
import ru.mystreet.bottomsheet.child.SheetChildContent
import ru.mystreet.bottomsheet.child.component.SheetChild
import ru.mystreet.bottomsheet.host.anchor.MergingSheetAnchor
import ru.mystreet.bottomsheet.host.anchor.SheetAnchor
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.AppComponentContextConfig
import ru.mystreet.core.component.ChildFactory
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildSlot

@OptIn(ExperimentalMaterial3Api::class)
class SheetHostComponent<Config : Any, Child : SheetChild>(
    componentContext: DIComponentContext,
    private val navigation: SlotNavigation<Config>,
    serializer: KSerializer<Config>,
    childFactory: ChildFactory<DIComponentContext, Config, Child>,
    override val childContent: SheetChildContent<Child>,
    id: String = "BottomSheetHost",
) : AppComponentContext(componentContext), SheetHost<Child> {

    override val config: AppComponentContextConfig = AppComponentContextConfig(id)

    private val mergingAnchor = MergingSheetAnchor()

    override val currentValue: SheetValue = mergingAnchor.currentValue
    override val forceValue: SheetValue
        get() = mergingAnchor.forceValue

    override val currentChild: Value<ChildSlot<*, Child>> = diChildSlot(
        key = id,
        source = navigation,
        serializer = serializer,
        childFactory = childFactory::createChild,
    )

    override fun setAnchor(sheetAnchor: SheetAnchor) {
        mergingAnchor.setAnchor(sheetAnchor)
    }

    override fun clearAnchor() {
        mergingAnchor.clearAnchor()
    }

    override fun confirmValueChange(state: SheetValue): Boolean {
        return true
    }

    override fun show() {
        mergingAnchor.show()
    }

    override fun hide() {
        mergingAnchor.hide()
    }

}