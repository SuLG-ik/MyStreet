package ru.mystreet.app.di

import ru.mystreet.app.AssetsStore
import dev.icerock.moko.resources.ImageResource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.app.MR

class MokoAssetsStore : AssetsStore {
    override val userLocationAsset: ImageResource = MR.images.user_location
}


class MokoAssetsModule : ModuleHost {

    override val module: Module = module {
        singleOf(::MokoAssetsStore) bind AssetsStore::class
    }


}