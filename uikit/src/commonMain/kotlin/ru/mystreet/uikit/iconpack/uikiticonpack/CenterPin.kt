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

public val UIKitIconPack.CenterPin: ImageVector
    get() {
        if (_centerPin != null) {
            return _centerPin!!
        }
        _centerPin = Builder(name = "CenterPin", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 52.0f, viewportHeight = 52.0f).apply {
            path(fill = SolidColor(Color(0xFF1081E0)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(38.853f, 5.324f)
                curveToRelative(-7.098f, -7.098f, -18.607f, -7.098f, -25.706f, 0.0f)
                curveTo(6.751f, 11.72f, 6.031f, 23.763f, 11.459f, 31.0f)
                lineTo(26.0f, 52.0f)
                lineToRelative(14.541f, -21.0f)
                curveToRelative(5.428f, -7.237f, 4.708f, -19.28f, -1.688f, -25.676f)
                close()
                moveTo(26.177f, 24.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, true, true, 0.0f, -12.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, true, 0.0f, 12.0f)
                close()
            }
        }
        .build()
        return _centerPin!!
    }

private var _centerPin: ImageVector? = null
