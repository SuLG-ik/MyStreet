package ru.mystreet.app

import androidx.compose.ui.window.ComposeUIViewController
import ru.mystreet.root.component.AppRoot
import ru.mystreet.root.ui.AppRootUI

fun MainViewController(root: AppRoot) = ComposeUIViewController { AppRootUI(root) }
