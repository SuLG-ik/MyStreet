package ru.mystreet.app.resources

import androidx.compose.ui.graphics.vector.ImageVector
import ru.mystreet.app.resources.iconpack.Follow
import ru.mystreet.app.resources.iconpack.Home
import ru.mystreet.app.resources.iconpack.Parks
import ru.mystreet.app.resources.iconpack.Search
import ru.mystreet.app.resources.iconpack.Trash
import ru.mystreet.app.resources.iconpack.ZoomIn
import ru.mystreet.app.resources.iconpack.ZoomOut
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Search, Home, ZoomIn, Follow, Trash, Parks, ZoomOut)
    return __AllIcons!!
  }
