package ru.sulgik.core.coil

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader

val LocalImageLoader = staticCompositionLocalOf<ImageLoader>{ error("ImageLoader is not provided") }