package ru.mystreet.app.di

import dev.icerock.moko.resources.ImageResource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.app.AssetsStore
import ru.mystreet.uikit.MR

class MokoAssetsStore : AssetsStore {
    override val userLocationAsset: ImageResource = MR.images.user_location
}


val platformMokoAssetsStore: Module = module {
    singleOf(::MokoAssetsStore) bind AssetsStore::class
}
