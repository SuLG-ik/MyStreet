package ru.mystreet.imagepicker.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.preat.peekaboo.image.picker.ResizeOptions
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.ui.CameraMode
import com.preat.peekaboo.ui.PeekabooCamera
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mystreet.imagepicker.component.ImagePicker
import ru.mystreet.imagepicker.domain.entity.ImageItem
import ru.mystreet.uikit.UIKitAsyncImage
import ru.mystreet.uikit.UIKitFilledTonalButton
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Accept
import ru.mystreet.uikit.iconpack.uikiticonpack.BackButton
import ru.mystreet.uikit.iconpack.uikiticonpack.CaptureButton
import ru.mystreet.uikit.iconpack.uikiticonpack.DiskStorage
import ru.mystreet.uikit.iconpack.uikiticonpack.Remove

@Composable
fun ImagePickerUI(
    component: ImagePicker,
    modifier: Modifier,
) {
    ImagePickerScreen(
        isContinueAvailable = component.isContinueAvailable.subscribeAsState().value,
        images = component.images.subscribeAsState().value,
        onLoad = component::onLoad,
        onBack = component::onBack,
        onPick = component::onPick,
        onRemove = component::onRemove,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerScreen(
    isContinueAvailable: Boolean,
    images: List<ImageItem>,
    onPick: (List<ByteArray>) -> Unit,
    onLoad: () -> Unit,
    onBack: () -> Unit,
    onRemove: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val imagePickerLauncher =
        rememberImagePickerLauncher(
            selectionMode = SelectionMode.Multiple(5),
            scope = scope,
            resizeOptions = ResizeOptions(
                1000,
                1000,
                256_000L,
            ),
            onResult = {
                scope.launch(Dispatchers.Main) {
                    onPick(it)
                }
            },
        )
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            UIKitIconPack.BackButton,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text("Камера")
                },
            )
        }
    ) {
        PeekabooCamera(
            modifier = Modifier.fillMaxSize().padding(it),
            cameraMode = CameraMode.Back,
            captureIcon = {
                CaptureIconOverlay(
                    isContinueAvailable = isContinueAvailable,
                    onCapture = it,
                    images = images,
                    onRemove = onRemove,
                    onLoadFromDisk = { imagePickerLauncher.launch() },
                    onAccept = { onLoad() },
                    onCancel = onBack,
                    modifier = Modifier.fillMaxSize().padding(bottom = 15.dp),
                )
            },
            onCapture = {
                if (it != null)
                    scope.launch(Dispatchers.Main) {
                        onPick(listOf(it))
                    }
            },
            permissionDeniedContent = {
                Text("no permission")
            }
        )
    }
}

@Composable
fun CaptureIconOverlay(
    isContinueAvailable: Boolean,
    images: List<ImageItem>,
    onCapture: () -> Unit,
    onRemove: (index: Int) -> Unit,
    onCancel: () -> Unit,
    onAccept: () -> Unit,
    onLoadFromDisk: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CameraControls(
            isAcceptAvailable = isContinueAvailable,
            onCapture = onCapture,
            onCancel = onCancel,
            onAccept = onAccept,
            modifier = Modifier.fillMaxWidth()
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            item(
                contentType = { "disk_storage" }
            ) {
                SelectFromStorage(
                    onLoadFromDisk = onLoadFromDisk,
                )
            }
            items(
                images.size,
                key = { images[it].uniqueId },
                contentType = { "image" },
            ) { index ->
                val item = images[index]
                Image(
                    image = item.content,
                    onRemove = { onRemove(index) },
                )
            }
        }
    }
}

@Composable
fun SelectFromStorage(
    onLoadFromDisk: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onLoadFromDisk,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier.size(75.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                UIKitIconPack.DiskStorage,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Text("Выбрать")
        }
    }
}

@Composable
fun CameraControls(
    isAcceptAvailable: Boolean,
    onCapture: () -> Unit,
    onCancel: () -> Unit,
    onAccept: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            UIKitFilledTonalButton(
                onClick = onCancel,
                modifier = Modifier.size(75.dp),
                color = MaterialTheme.colorScheme.surface,
            ) {
                Icon(
                    UIKitIconPack.BackButton,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )
            }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Image(
                UIKitIconPack.CaptureButton,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onCapture)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isAcceptAvailable,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                UIKitFilledTonalButton(
                    onClick = onAccept,
                    modifier = Modifier.size(75.dp),
                ) {
                    Icon(
                        UIKitIconPack.Accept,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Image(
    image: ByteArray,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BadgedBox(
        badge = {
            Badge {
                Icon(
                    UIKitIconPack.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(8.dp),
                )
            }
        },
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onRemove,
        )
    ) {
        UIKitAsyncImage(
            image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(75.dp).clip(MaterialTheme.shapes.medium),
        )
    }
}