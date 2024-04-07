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

public val UIKitIconPack.RatingStar: ImageVector
    get() {
        if (_ratingStar != null) {
            return _ratingStar!!
        }
        _ratingStar = Builder(name = "RatingStar", defaultWidth = 12.0.dp, defaultHeight = 12.0.dp,
                viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFFEEA63A)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.9f, 12.0f)
                curveToRelative(-0.2f, 0.0f, -0.4f, -0.1f, -0.6f, -0.2f)
                curveToRelative(-0.3f, -0.2f, -0.5f, -0.6f, -0.4f, -1.0f)
                lineTo(2.4f, 8.0f)
                curveToRelative(0.0f, -0.1f, 0.0f, -0.3f, -0.1f, -0.4f)
                lineToRelative(-2.0f, -2.0f)
                curveTo(0.0f, 5.3f, -0.1f, 4.9f, 0.0f, 4.5f)
                curveToRelative(0.1f, -0.4f, 0.4f, -0.7f, 0.8f, -0.7f)
                lineToRelative(2.7f, -0.4f)
                curveToRelative(0.1f, 0.0f, 0.2f, -0.1f, 0.3f, -0.2f)
                lineToRelative(1.2f, -2.6f)
                curveTo(5.3f, 0.2f, 5.6f, 0.0f, 6.0f, 0.0f)
                curveToRelative(0.4f, 0.0f, 0.7f, 0.2f, 0.9f, 0.6f)
                lineToRelative(1.2f, 2.6f)
                curveToRelative(0.1f, 0.1f, 0.2f, 0.2f, 0.3f, 0.2f)
                lineToRelative(2.7f, 0.4f)
                curveToRelative(0.4f, 0.1f, 0.7f, 0.3f, 0.8f, 0.7f)
                curveToRelative(0.1f, 0.4f, 0.0f, 0.8f, -0.3f, 1.1f)
                lineToRelative(-2.0f, 2.0f)
                curveTo(9.6f, 7.7f, 9.6f, 7.8f, 9.6f, 8.0f)
                lineToRelative(0.5f, 2.8f)
                curveToRelative(0.1f, 0.4f, -0.1f, 0.8f, -0.4f, 1.0f)
                curveTo(9.4f, 12.0f, 9.0f, 12.1f, 8.6f, 11.9f)
                lineToRelative(-2.4f, -1.3f)
                curveToRelative(-0.1f, -0.1f, -0.3f, -0.1f, -0.4f, 0.0f)
                lineToRelative(-2.4f, 1.3f)
                curveTo(3.2f, 12.0f, 3.1f, 12.0f, 2.9f, 12.0f)
                close()
            }
        }
        .build()
        return _ratingStar!!
    }

private var _ratingStar: ImageVector? = null
