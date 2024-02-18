package ru.mystreet.uikit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min

@Composable
fun Modifier.paddingVerticalInsets() =
    this then Modifier.padding(
        PaddingValues(
            bottom = maxOf(
                ScaffoldDefaults.contentWindowInsets.asPaddingValues().calculateBottomPadding(),
                20.dp
            ),
            top = ScaffoldDefaults.contentWindowInsets.asPaddingValues().calculateTopPadding(),
        )
    )
@Composable
fun Modifier.paddingBottomInsets() =
    this then Modifier.padding(
        PaddingValues(
            bottom = maxOf(
                ScaffoldDefaults.contentWindowInsets.asPaddingValues().calculateBottomPadding(),
                20.dp
            ),
        )
    )

@Composable
fun Modifier.paddingHorizontalInsets() =
    this then Modifier.padding(
        PaddingValues(
            start = maxOf(
                ScaffoldDefaults.contentWindowInsets.asPaddingValues()
                    .calculateStartPadding(LocalLayoutDirection.current),
                10.dp,
            ),
            end = maxOf(
                ScaffoldDefaults.contentWindowInsets.asPaddingValues()
                    .calculateEndPadding(LocalLayoutDirection.current),
                10.dp,
            ),
        )
    )

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