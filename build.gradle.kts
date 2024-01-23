plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.kotlinxSerialization).apply(false)
    alias(libs.plugins.buildKonfig).apply(false)
    alias(libs.plugins.moko.resources).apply(false)
}
