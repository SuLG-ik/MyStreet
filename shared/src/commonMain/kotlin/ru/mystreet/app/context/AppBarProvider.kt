package ru.mystreet.app.context

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface AppBarProvider {

    @Composable
    fun AppBarContent(modifier: Modifier)

}