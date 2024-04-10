package ru.mystreet.errors.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.collections.immutable.ImmutableList
import ru.mystreet.errors.component.ErrorsList
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.TimedError
import kotlin.time.Duration

@Composable
fun ErrorsListUI(
    component: ErrorsList,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    val errors by component.items.subscribeAsState()
    ErrorsListScreen(
        errors = errors,
        modifier = modifier,
        content = content,
    )
}

@Composable
fun ErrorsListScreen(
    errors: ImmutableList<TimedError>,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Box {
        content()
        ErrorsHost(
            errors = errors,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ErrorsHost(
    errors: ImmutableList<TimedError>,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(errors, key = { it.info.uniqueId }, contentType = { "error" }) {
            ErrorItem(
                error = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement()
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Composable
fun ErrorItem(
    error: TimedError,
    modifier: Modifier,
) {
    when (val data = error.info.data) {
        is ErrorInfo.ErrorData.Raw -> RawErrorItem(error.info, data, modifier)
    }
}

@Composable
fun rememberProgressFrom(
    duration: Duration,
): Float {
    val progress = remember { mutableFloatStateOf(1f) }
    LaunchedEffect(duration) {
        var startMillis: Long = 0
        var endMillis: Long = 0
        while (true) {
            withFrameMillis {
                if (startMillis == 0L) {
                    startMillis = it
                    endMillis = it + duration.inWholeMilliseconds
                }
                val delta = endMillis - startMillis
                val millisFromStart = it - startMillis
                progress.value = (millisFromStart / delta.toFloat()).coerceIn(0f, 1f)
            }
        }
    }
    return progress.value
}

@Composable
fun RawErrorItem(
    info: ErrorInfo,
    data: ErrorInfo.ErrorData.Raw,
    modifier: Modifier = Modifier,
) {
    val progress = rememberProgressFrom(info.config.duration)
    val color = (data.color ?: MaterialTheme.colorScheme.surfaceContainerLow).copy(alpha = 0.9f)
    val backgroundColor =
        (data.color ?: MaterialTheme.colorScheme.surfaceContainerHighest).copy(0.9f)
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .drawWithContent {
                drawRect(backgroundColor)
                drawRect(
                    color,
                    size = size.copy(width = size.width * (1 - progress)),
                )
                drawContent()
            },
    ) {
        Row {
            val icon = data.icon
            if (icon != null) {
                Icon(
                    icon,
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    data.title,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                val content = data.content
                if (content != null) {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                    )
                }
            }
        }
    }
}