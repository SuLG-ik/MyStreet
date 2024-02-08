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

public val UIKitIconPack.Streetlight: ImageVector
    get() {
        if (_streetlight != null) {
            return _streetlight!!
        }
        _streetlight = Builder(name = "Streetlight", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.0104f, 12.3824f)
                horizontalLineTo(8.9097f)
                verticalLineTo(11.6248f)
                horizontalLineTo(8.1945f)
                verticalLineTo(12.3824f)
                horizontalLineTo(7.0938f)
                verticalLineTo(13.1185f)
                horizontalLineTo(10.0104f)
                verticalLineTo(12.3824f)
                close()
                moveTo(12.1979f, 0.0f)
                verticalLineTo(0.7361f)
                horizontalLineTo(12.934f)
                verticalLineTo(1.8618f)
                horizontalLineTo(13.3041f)
                curveTo(13.4753f, 2.9247f, 14.3743f, 3.7379f, 15.4549f, 3.7379f)
                curveTo(16.5353f, 3.7379f, 17.4344f, 2.9247f, 17.6056f, 1.8618f)
                horizontalLineTo(18.0f)
                verticalLineTo(0.0f)
                horizontalLineTo(12.1979f)
                close()
                moveTo(15.4549f, 3.0018f)
                curveTo(14.7702f, 3.0018f, 14.1939f, 2.516f, 14.0338f, 1.8618f)
                horizontalLineTo(16.8758f)
                curveTo(16.7158f, 2.516f, 16.1395f, 3.0018f, 15.4549f, 3.0018f)
                close()
                moveTo(17.2848f, 1.1257f)
                horizontalLineTo(13.6493f)
                verticalLineTo(0.7361f)
                horizontalLineTo(17.2848f)
                verticalLineTo(1.1257f)
                close()
                moveTo(8.1945f, 2.2441f)
                verticalLineTo(11.2495f)
                horizontalLineTo(8.9097f)
                verticalLineTo(2.2441f)
                curveTo(8.9097f, 1.4126f, 9.567f, 0.7361f, 10.375f, 0.7361f)
                horizontalLineTo(11.8333f)
                verticalLineTo(0.0f)
                horizontalLineTo(10.375f)
                curveTo(9.1727f, 0.0f, 8.1945f, 1.0068f, 8.1945f, 2.2441f)
                close()
                moveTo(10.0034f, 13.5081f)
                horizontalLineTo(7.1007f)
                verticalLineTo(23.2639f)
                horizontalLineTo(6.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(11.1017f)
                verticalLineTo(23.2639f)
                horizontalLineTo(10.0034f)
                verticalLineTo(13.5081f)
                close()
                moveTo(9.2882f, 23.2639f)
                horizontalLineTo(7.816f)
                verticalLineTo(21.7487f)
                horizontalLineTo(9.2882f)
                verticalLineTo(23.2639f)
                close()
                moveTo(9.2882f, 21.0126f)
                horizontalLineTo(7.816f)
                verticalLineTo(14.2442f)
                horizontalLineTo(9.2882f)
                verticalLineTo(21.0126f)
                close()
            }
        }
        .build()
        return _streetlight!!
    }

private var _streetlight: ImageVector? = null
