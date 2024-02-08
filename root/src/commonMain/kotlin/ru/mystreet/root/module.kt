package ru.mystreet.root

import org.koin.dsl.module
import ru.mystreet.map.root.mapHostModule

val rootModule = module {
    includes(mapHostModule)
}