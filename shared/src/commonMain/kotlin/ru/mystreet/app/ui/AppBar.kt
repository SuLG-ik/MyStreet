package ru.mystreet.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    startActions: @Composable (RowScope.() -> Unit)? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = modifier,
    ) {
        if (startActions != null)
            Row(
                modifier = Modifier.weight(1f),
            ) {
                startActions()
            }

        Column(
            modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.weight(1f),
            ) {
                endActions()
            }
    }
}