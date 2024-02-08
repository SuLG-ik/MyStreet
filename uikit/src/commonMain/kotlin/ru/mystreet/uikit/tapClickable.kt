package ru.mystreet.uikit

import androidx.compose.foundation.Indication
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope

fun Modifier.tapClickable(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onPress: () -> Unit,
    onPressRelease: () -> Unit,
) = inspectable(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onPress"] = onPress
        properties["onPressRelease"] = onPressRelease
    }
) {
    Modifier
        .indication(interactionSource, indication)
        .hoverable(enabled = enabled, interactionSource = interactionSource)
        .pointerInput(enabled, interactionSource) {
            coroutineScope {
                detectTapGestures(
                    onPress = {
                        val press = PressInteraction.Press(it)
                        try {
                            onPress()
                            interactionSource.emit(press)
                            awaitRelease()
                            onPressRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        } catch (e: CancellationException) {
                            onPressRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    }
                )
            }
        }
        .focusable(enabled = enabled, interactionSource = interactionSource)

}