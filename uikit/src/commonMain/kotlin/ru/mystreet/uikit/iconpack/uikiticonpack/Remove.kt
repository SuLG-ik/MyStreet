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

public val UIKitIconPack.Remove: ImageVector
    get() {
        if (_remove != null) {
            return _remove!!
        }
        _remove = Builder(name = "Remove", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.59f, 59.41f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.83f, 0.0f)
                lineTo(32.0f, 34.83f)
                lineToRelative(24.59f, 24.58f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.83f, -2.83f)
                lineTo(34.83f, 32.0f)
                lineTo(59.41f, 7.41f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, -2.83f, -2.83f)
                lineTo(32.0f, 29.17f)
                lineTo(7.41f, 4.59f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, -2.82f, 2.82f)
                lineTo(29.17f, 32.0f)
                lineTo(4.59f, 56.59f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 0.0f, 2.82f)
                close()
            }
        }
        .build()
        return _remove!!
    }

private var _remove: ImageVector? = null
