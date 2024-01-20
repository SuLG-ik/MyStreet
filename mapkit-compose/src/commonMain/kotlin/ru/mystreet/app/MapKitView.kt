package ru.mystreet.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MapView(mapController: MapController, modifier: Modifier)