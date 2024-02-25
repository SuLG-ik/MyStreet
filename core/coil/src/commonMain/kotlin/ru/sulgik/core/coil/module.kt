package ru.sulgik.core.coil

import org.koin.dsl.module

val coilModule = module {
    includes(platformCoilModule)
    single { newImageLoader(get(), true) }
}