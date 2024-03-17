package ru.mystreet.uikit.iconpack

import androidx.compose.ui.graphics.vector.ImageVector
import ru.mystreet.uikit.iconpack.uikiticonpack.Accept
import ru.mystreet.uikit.iconpack.uikiticonpack.AccountIcon
import ru.mystreet.uikit.iconpack.uikiticonpack.Add
import ru.mystreet.uikit.iconpack.uikiticonpack.AddOutlined
import ru.mystreet.uikit.iconpack.uikiticonpack.AppIcon
import ru.mystreet.uikit.iconpack.uikiticonpack.ArrowUp
import ru.mystreet.uikit.iconpack.uikiticonpack.Bench
import ru.mystreet.uikit.iconpack.uikiticonpack.Bower
import ru.mystreet.uikit.iconpack.uikiticonpack.CaptureButton
import ru.mystreet.uikit.iconpack.uikiticonpack.CenterPin
import ru.mystreet.uikit.iconpack.uikiticonpack.DiskStorage
import ru.mystreet.uikit.iconpack.uikiticonpack.EditIcon
import ru.mystreet.uikit.iconpack.uikiticonpack.Follow
import ru.mystreet.uikit.iconpack.uikiticonpack.Fountain
import ru.mystreet.uikit.iconpack.uikiticonpack.GreenZone
import ru.mystreet.uikit.iconpack.uikiticonpack.Home
import ru.mystreet.uikit.iconpack.uikiticonpack.Layers
import ru.mystreet.uikit.iconpack.uikiticonpack.Monument
import ru.mystreet.uikit.iconpack.uikiticonpack.NoCamera
import ru.mystreet.uikit.iconpack.uikiticonpack.Parks
import ru.mystreet.uikit.iconpack.uikiticonpack.Playground
import ru.mystreet.uikit.iconpack.uikiticonpack.PublicWc
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStarOutline
import ru.mystreet.uikit.iconpack.uikiticonpack.Remove
import ru.mystreet.uikit.iconpack.uikiticonpack.Search
import ru.mystreet.uikit.iconpack.uikiticonpack.Streetlight
import ru.mystreet.uikit.iconpack.uikiticonpack.Trash
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomIn
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomOut
import kotlin.collections.List as ____KtList

public object UIKitIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val UIKitIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(NoCamera, Search, CaptureButton, Fountain, Home, RatingStarOutline, PublicWc,
        ZoomIn, EditIcon, Streetlight, Follow, AddOutlined, Bench, AppIcon, Layers, ArrowUp,
        RatingStar, Add, GreenZone, Monument, DiskStorage, Trash, Accept, Bower, AccountIcon, Parks,
        CenterPin, Remove, Playground, ZoomOut)
    return __AllIcons!!
  }
