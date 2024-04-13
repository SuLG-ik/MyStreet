package ru.mystreet.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.AuthPasswordShown: ImageVector
    get() {
        if (_authPasswordShown != null) {
            return _authPasswordShown!!
        }
        _authPasswordShown = Builder(
            name = "AuthPasswordShown", defaultWidth = 25.0.dp,
            defaultHeight = 25.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 20.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(25.1f, 256.0f)
                arcToRelative(230.9f, 136.4f, 0.0f, true, false, 461.8f, 0.0f)
                arcToRelative(230.9f, 136.4f, 0.0f, true, false, -461.8f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 20.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(256.0f, 256.0f)
                moveToRelative(-79.9f, 0.0f)
                arcToRelative(79.9f, 79.9f, 0.0f, true, true, 159.8f, 0.0f)
                arcToRelative(79.9f, 79.9f, 0.0f, true, true, -159.8f, 0.0f)
            }
        }
            .build()
        return _authPasswordShown!!
    }

private var _authPasswordShown: ImageVector? = null
