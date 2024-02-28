package ru.sulgik.core.coil

import coil3.PlatformContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformCoilModule: Module = module {
    single { PlatformContext.INSTANCE }
}