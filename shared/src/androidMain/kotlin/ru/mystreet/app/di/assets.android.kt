package ru.mystreet.app.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.mapkit.moko.AndroidMOKOImageLoader
import ru.sulgik.mapkit.moko.MOKOImageLoader

actual val platformImageModule: Module = module {
    singleOf(::AndroidMOKOImageLoader) bind MOKOImageLoader::class
}