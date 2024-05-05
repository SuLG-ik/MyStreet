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

public val UIKitIconPack.ArrowRight: ImageVector
    get() {
        if (_arrowRight != null) {
            return _arrowRight!!
        }
        _arrowRight = Builder(name = "ArrowRight", defaultWidth = 512.0.dp, defaultHeight =
                512.0.dp, viewportWidth = 492.004f, viewportHeight = 492.004f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(382.678f, 226.804f)
                lineTo(163.73f, 7.86f)
                curveTo(158.666f, 2.792f, 151.906f, 0.0f, 144.698f, 0.0f)
                reflectiveCurveToRelative(-13.968f, 2.792f, -19.032f, 7.86f)
                lineToRelative(-16.124f, 16.12f)
                curveToRelative(-10.492f, 10.504f, -10.492f, 27.576f, 0.0f, 38.064f)
                lineTo(293.398f, 245.9f)
                lineToRelative(-184.06f, 184.06f)
                curveToRelative(-5.064f, 5.068f, -7.86f, 11.824f, -7.86f, 19.028f)
                curveToRelative(0.0f, 7.212f, 2.796f, 13.968f, 7.86f, 19.04f)
                lineToRelative(16.124f, 16.116f)
                curveToRelative(5.068f, 5.068f, 11.824f, 7.86f, 19.032f, 7.86f)
                reflectiveCurveToRelative(13.968f, -2.792f, 19.032f, -7.86f)
                lineTo(382.678f, 265.0f)
                curveToRelative(5.076f, -5.084f, 7.864f, -11.872f, 7.848f, -19.088f)
                curveToRelative(0.016f, -7.244f, -2.772f, -14.028f, -7.848f, -19.108f)
                close()
            }
        }
        .build()
        return _arrowRight!!
    }

private var _arrowRight: ImageVector? = null
