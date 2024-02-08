package ru.mystreet.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.sp

@Composable
fun UIKitAppBar(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    startActions: @Composable (RowScope.() -> Unit)? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
) {
    Box(
        modifier = modifier.paddingHorizontalInsets(),
    ) {
        if (startActions != null)
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                startActions()
            }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                    color = Color.White,
                    shadow = Shadow(blurRadius = 12f)
                ),
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 18.sp,
                    color = Color.White,
                    shadow = Shadow(blurRadius = 12f),
                ),
            )
        }

        if (endActions != null)
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                endActions()
            }
    }
}