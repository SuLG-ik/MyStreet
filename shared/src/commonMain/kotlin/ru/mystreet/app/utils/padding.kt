package ru.mystreet.app.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min

@Composable
fun min(first: PaddingValues, second: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = min(
            first.calculateStartPadding(layoutDirection),
            second.calculateStartPadding(layoutDirection)
        ),
        top = min(
            first.calculateTopPadding(),
            second.calculateTopPadding()
        ),
        end = min(
            first.calculateEndPadding(layoutDirection),
            second.calculateEndPadding(layoutDirection)
        ),
        bottom = min(
            first.calculateBottomPadding(),
            second.calculateBottomPadding()
        ),
    )
}
@Composable
fun max(first: PaddingValues, second: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = max(
            first.calculateStartPadding(layoutDirection),
            second.calculateStartPadding(layoutDirection)
        ),
        top = max(
            first.calculateTopPadding(),
            second.calculateTopPadding()
        ),
        end = max(
            first.calculateEndPadding(layoutDirection),
            second.calculateEndPadding(layoutDirection)
        ),
        bottom = max(
            first.calculateBottomPadding(),
            second.calculateBottomPadding()
        ),
    )
}