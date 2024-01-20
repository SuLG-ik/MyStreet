package ru.mystreet.app

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplicationDelegateProtocol
import platform.darwin.NSObject
import ru.mystreet.app.context.RootComponent

fun MainViewController(root: RootComponent) = ComposeUIViewController { root.RootContent() }
