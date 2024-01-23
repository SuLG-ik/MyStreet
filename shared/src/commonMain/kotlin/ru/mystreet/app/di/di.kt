package ru.mystreet.app.di

import org.koin.core.module.Module
import org.koin.dsl.module

interface ModuleHost {

    val module: Module

}

class AppModule: ModuleHost {

    override val module = module {
        includes(PlatformYandexMapModule().module, MokoAssetsModule().module)
    }

}

expect class PlatformYandexMapModule(): ModuleHost