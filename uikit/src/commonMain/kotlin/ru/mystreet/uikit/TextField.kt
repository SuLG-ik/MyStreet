package ru.mystreet.uikit

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import arrow.core.Ior
import kotlinx.collections.immutable.ImmutableCollection
import kotlinx.serialization.Serializable
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AuthPasswordHiden
import ru.mystreet.uikit.iconpack.uikiticonpack.AuthPasswordShown
import ru.mystreet.uikit.placeholder.placeholder

@Serializable
data class ValidatedField<T>(
    val value: String,
    val error: T?,
)

val KeyboardActionsNext: KeyboardActions
    @Composable
    get() {
        val focusManager = LocalFocusManager.current
        return KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Next)
        })
    }

val KeyboardOptionsNext: KeyboardOptions
    get() {
        return KeyboardOptions(imeAction = ImeAction.Next)
    }

val KeyboardActionsDone: KeyboardActions
    @Composable
    get() {
        val focusManager = LocalFocusManager.current
        return KeyboardActions(onNext = {
            focusManager.clearFocus()
        })
    }

val KeyboardOptionsDone: KeyboardOptions
    get() {
        return KeyboardOptions(imeAction = ImeAction.Done)
    }

@Composable
fun UIKitTextFieldPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.defaultMinSize(minWidth = 250.dp).height(56.dp).placeholder()
    )
}

@Composable
fun <T> UIKitValidatedOutlinedTextField(
    title: String,
    value: ValidatedField<T>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    errorText: @Composable ((T) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    UIKitOutlineTextField(
        title = title,
        value = value.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        errorText = {
            if (value.error != null) {
                errorText?.invoke(value.error)
            }
        },
        isError = value.error != null,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
    )
}

@Composable
fun <T> UIKitValidatedOutlinedPasswordField(
    title: String,
    value: ValidatedField<T>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    errorText: @Composable ((T) -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val isHidden = remember { mutableStateOf(true) }
    UIKitOutlineTextField(
        title = title,
        value = value.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(
                onClick = {
                    isHidden.value = !isHidden.value
                }
            ) {
                Crossfade(isHidden.value) {
                    if (it) {
                        Icon(
                            UIKitIconPack.AuthPasswordHiden,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Icon(
                            UIKitIconPack.AuthPasswordShown,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        },
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        errorText = {
            if (value.error != null) {
                errorText?.invoke(value.error)
            }
        },
        isError = value.error != null,
        visualTransformation = if (isHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
    )
}

@Composable
fun <T> UIKitValidatedOutlinedTextField(
    title: String,
    value: Ior<T, String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    errorText: @Composable ((T) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    UIKitOutlineTextField(
        title = title,
        value = value.getOrNull() ?: "",
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        errorText = {
            value.leftOrNull()?.let { errorText?.invoke(it) }
        },
        isError = value.leftOrNull() != null,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
    )
}

@Composable
fun UIKitOutlineTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    errorText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    Column(
        modifier = modifier,
    ) {
        val isInFocus = remember { mutableStateOf(false) }
        val isInputOnce = remember { mutableStateOf(false) }
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isInFocus.value = true
                isInputOnce.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    if (!it.hasFocus)
                        isInFocus.value = false
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            isError = isInputOnce.value && !isInFocus.value && isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            shape = MaterialTheme.shapes.medium,
            colors = colors,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.error)) {
                    if (isInputOnce.value && !isInFocus.value && isError)
                        errorText?.invoke()
                }
            }
            ProvideTextStyle(value = MaterialTheme.typography.titleSmall) {
                supportingText?.invoke()
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> UIKitOutlineTextFieldWithChips(
    title: String,
    value: String,
    chips: ImmutableCollection<T>,
    completePainter: Painter,
    placeholder: String,
    onComplete: () -> Unit,
    chip: @Composable (T) -> Unit = { Text(it.toString()) },
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    supportingText: @Composable (() -> Unit)? = null,
    errorText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val density = LocalDensity.current
    val iconSize = with(density) { textStyle.lineHeight.toDp() }
    Column(
        modifier = modifier,
    ) {
        val isInFocus = remember { mutableStateOf(false) }
        val isInputOnce = remember { mutableStateOf(false) }
        Text(text = title, style = MaterialTheme.typography.titleMedium)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(space = ChipsSpaceBetween),
            verticalArrangement = Arrangement.spacedBy(
                space = ChipsSpaceBetween,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            chips.forEach {
                chip(it)
            }
            if (enabled)
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.onFocusChanged {
                        if (!it.hasFocus) {
                            onComplete()
                        }
                    },
                    enabled = enabled,
                    readOnly = readOnly,
                    textStyle = textStyle,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    singleLine = true,
                    interactionSource = interactionSource,
                    decorationBox = {
                        Box {
                            Box(
                                modifier = Modifier
                                    .padding(end = iconSize + ChipFieldEndPadding)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = CircleShape
                                    ).padding(ChipInnerPadding),
                            ) {
                                if (value.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        color = colors.focusedPlaceholderColor,
                                    )
                                }

                                it()
                            }
                            Icon(
                                painter = completePainter,
                                contentDescription = null,
                                modifier = Modifier.clip(CircleShape)
                                    .clickable(onClick = onComplete)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = CircleShape
                                    ).padding(IconInnerPadding).size(iconSize)
                                    .padding(IconInnerPadding)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    },
                )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.error)) {
                    if (isInputOnce.value && !isInFocus.value && isError)
                        errorText?.invoke()
                }
            }
            ProvideTextStyle(value = MaterialTheme.typography.titleSmall) {
                supportingText?.invoke()
            }
        }
    }
}

private val ChipsSpaceBetween = 7.5.dp
private val ChipInnerPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp)
private val IconInnerPadding = PaddingValues(5.dp)
private val ChipFieldEndPadding = ChipsSpaceBetween + 10.dp