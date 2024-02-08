package ru.mystreet.map.root

import org.koin.dsl.module
import ru.mystreet.map.general.generalMapModule

val mapHostModule = module {
    includes(generalMapModule)
}