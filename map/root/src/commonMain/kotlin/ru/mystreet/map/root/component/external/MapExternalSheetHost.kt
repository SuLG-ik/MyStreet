package ru.mystreet.map.root.component.external

import ru.mystreet.account.component.AccountHost
import ru.mystreet.bottomsheet.SheetConfig
import ru.mystreet.bottomsheet.child.component.SheetChild
import ru.mystreet.bottomsheet.host.component.SheetHost
import ru.mystreet.map.mapobject.component.info.MapObjectInfoHost

interface MapExternalSheetHost : SheetHost<MapExternalSheetHost.Child> {

    sealed interface Child : SheetChild {
        class Account(val component: AccountHost) : Child {
            override val config: SheetConfig =
                SheetConfig(
                    id = "account",
                    expand = SheetConfig.Expand.VISIBLE_TOP,
                )
        }

        class MapObjectInfo(val component: MapObjectInfoHost) : Child {
            override val config: SheetConfig =
                SheetConfig(id = "map_object")
        }
    }

}