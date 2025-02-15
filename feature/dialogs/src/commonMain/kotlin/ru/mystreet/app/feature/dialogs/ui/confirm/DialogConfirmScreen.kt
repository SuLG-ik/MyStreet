package ru.mystreet.app.feature.dialogs.ui.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class DialogConfirmScreenState(
    val title: String,
    val text: String?,
    val confirm: String,
    val cancel: String?,
    val titleAlign: TextAlign?,
    val reverseActions: Boolean,
)

@Composable
fun DialogConfirmScreen(
    state: DialogConfirmScreenState,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MSPopupContent(
        title = state.title,
        actions = state.actions(onConfirm = onConfirm, onCancel = onCancel),
        modifier = modifier,
        titleAlign = state.titleAlign,
        contentText = state.text,
    )
}

private fun DialogConfirmScreenState.actions(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
): PersistentList<WTPopup.Action> {
    return if (cancel == null) {
        persistentListOf(
            WTPopup.Action(
                text = confirm,
                onClick = onConfirm,
            ),
        )
    } else {
        if (reverseActions) {
            persistentListOf(
                WTPopup.Action(
                    text = cancel,
                    onClick = onCancel,
                ),
                WTPopup.Action(
                    text = confirm,
                    onClick = onConfirm,
                ),
            )
        } else {
            persistentListOf(
                WTPopup.Action(
                    text = confirm,
                    onClick = onConfirm,
                ),
                WTPopup.Action(
                    text = cancel,
                    onClick = onCancel,
                ),
            )
        }
    }
}


object WTPopup {

    class Colors(
        val backgroundColor: Color,
        val titleColor: Color,
        val contentColor: Color,
    )

    class Action(
        val text: String,
        val onClick: () -> Unit,
    )

    class Text(
        val text: String,
        val textStyle: TextStyle,
    )
}

@Composable
fun MSPopupContent(
    title: String,
    actions: ImmutableList<WTPopup.Action>,
    modifier: Modifier = Modifier,
    contentText: String? = null,
    colors: WTPopup.Colors = WTPopup.Colors(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        titleColor = MaterialTheme.colorScheme.onBackground,
    ),
    titleAlign: TextAlign? = null,
    content: @Composable () -> Unit = {},
) {
    MSPopupContent(
        title = WTPopup.Text(title, MaterialTheme.typography.titleLarge),
        contentText = contentText?.let { WTPopup.Text(it, MaterialTheme.typography.bodyLarge) },
        content = content,
        actions = actions,
        colors = colors,
        titleAlign = titleAlign,
        modifier = modifier,
    )
}

@Composable
internal fun MSPopupContent(
    title: WTPopup.Text,
    colors: WTPopup.Colors,
    actions: ImmutableList<WTPopup.Action>,
    modifier: Modifier = Modifier,
    contentText: WTPopup.Text? = null,
    titleAlign: TextAlign? = null,
    content: @Composable () -> Unit = {},
) {
    val contentSpacing = 16.dp
    MSPopupContent(
        colors = colors,
        modifier = modifier,
        header = {
            Text(
                text = title.text,
                overflow = TextOverflow.Ellipsis,
                style = title.textStyle,
                textAlign = titleAlign,
            )
        },
        actions = if (actions.isNotEmpty()) {
            {
                val actionsSpacing = 8.dp
                Row(
                    horizontalArrangement = Arrangement.spacedBy(actionsSpacing, Alignment.End),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    actions.forEach {
                        TextButton(
                            onClick = it.onClick,
                        ) {
                            Text(text = it.text,)
                        }
                    }
                }
            }
        } else {
            null
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(contentSpacing)
            ) {
                if (contentText != null) {
                    Text(
                        text = contentText.text,
                        style = contentText.textStyle,
                    )
                }
                content()
            }
        }
    )
}

@Composable
internal fun MSPopupContent(
    colors: WTPopup.Colors,
    modifier: Modifier = Modifier,
    header: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    val rounding = MaterialTheme.shapes.medium
    val contentSpacing = 16.dp
    val actionsContainerSpacing = 8.dp
    Column(
        modifier = modifier
            .clip(rounding)
            .background(colors.backgroundColor)
            .padding(16.dp),
    ) {
        if (header != null) {
            CompositionLocalProvider(
                LocalContentColor provides colors.titleColor
            ) {
                header()
            }
        }
        if (content != null) {
            Spacer(modifier = Modifier.height(contentSpacing))
            CompositionLocalProvider(
                LocalContentColor provides colors.contentColor
            ) {
                content()
            }
        }
        if (actions != null) {
            Spacer(modifier = Modifier.height(actionsContainerSpacing))
            actions()
        }
    }
}

