package ru.mystreet.map.root.component.external

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.account.component.AccountHostComponent
import ru.mystreet.app.MapController
import ru.mystreet.bottomsheet.child.SheetChildContent
import ru.mystreet.bottomsheet.host.anchor.SheetAnchor
import ru.mystreet.bottomsheet.host.component.SheetHost
import ru.mystreet.bottomsheet.host.component.SheetHostComponent
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.component.Map
import ru.mystreet.map.component.info.MapObjectInfoHostComponent

@OptIn(ExperimentalMaterial3Api::class)
class MapExternalSheetHostComponent(
    diComponentContext: DIComponentContext,
    private val navigation: SlotNavigation<Config> = SlotNavigation(),
    private val map: Map,
) : MapExternalSheetHost {

    private val sheet: SheetHost<MapExternalSheetHost.Child> = SheetHostComponent(
        componentContext = diComponentContext.diChildContext("map_sheet_host"),
        navigation = navigation,
        serializer = Config.serializer(),
        childFactory = { config, context ->
            when (config) {
                Config.Account -> MapExternalSheetHost.Child.Account(
                    AccountHostComponent(context)
                )

                is Config.MapInfo -> MapExternalSheetHost.Child.MapObjectInfo(
                    MapObjectInfoHostComponent(context, this::onBack, config.mapObjectId, map = map)
                )
            }
        },
        childContent = MapExternalSheetChildContent(),
    )

    private fun onBack() {

    }

    fun onAccount() {
        navigation.activate(Config.Account)
        show()
    }

    fun onMapObjectInfo(mapObjectId: Long) {
        navigation.activate(Config.MapInfo(mapObjectId))
        show()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object Account : Config()

        @Serializable
        data class MapInfo(
            val mapObjectId: Long,
        ) : Config()
    }

    override val currentValue: SheetValue
        get() = sheet.currentValue
    override val forceValue: SheetValue
        get() = sheet.forceValue

    override val childContent: SheetChildContent<MapExternalSheetHost.Child>
        get() = sheet.childContent
    override val currentChild: Value<ChildSlot<*, MapExternalSheetHost.Child>>
        get() = sheet.currentChild

    override fun setAnchor(sheetAnchor: SheetAnchor) {
        sheet.setAnchor(sheetAnchor)
    }

    override fun clearAnchor() {
        sheet.clearAnchor()
    }

    override fun confirmValueChange(state: SheetValue): Boolean {
        return sheet.confirmValueChange(state)
    }

    override fun show() {
        sheet.show()
    }

}