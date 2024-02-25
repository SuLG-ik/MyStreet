package ru.mystreet.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.DiskStorage: ImageVector
    get() {
        if (_diskStorage != null) {
            return _diskStorage!!
        }
        _diskStorage = Builder(name = "DiskStorage", defaultWidth = 512.0.dp, defaultHeight =
                512.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(6.244f, 5.0f)
                arcToRelative(0.497f, 0.497f, 0.0f, false, false, -0.479f, 0.357f)
                lineTo(3.16f, 14.039f)
                curveToRelative(0.11f, -0.025f, 0.223f, -0.039f, 0.34f, -0.039f)
                horizontalLineToRelative(16.0f)
                curveToRelative(0.117f, 0.0f, 0.23f, 0.014f, 0.34f, 0.039f)
                lineToRelative(-2.605f, -8.682f)
                arcTo(0.503f, 0.503f, 0.0f, false, false, 16.756f, 5.0f)
                close()
                moveTo(17.5f, 16.5f)
                horizontalLineToRelative(0.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, 0.0f, 1.0f)
                horizontalLineToRelative(-0.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, 0.0f, -1.0f)
                close()
                moveTo(14.5f, 16.5f)
                horizontalLineToRelative(0.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, 0.0f, 1.0f)
                horizontalLineToRelative(-0.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, true, true, 0.0f, -1.0f)
                close()
                moveTo(3.0f, 16.5f)
                verticalLineToRelative(2.0f)
                curveToRelative(0.0f, 0.275f, 0.225f, 0.5f, 0.5f, 0.5f)
                horizontalLineToRelative(16.0f)
                curveToRelative(0.275f, 0.0f, 0.5f, -0.225f, 0.5f, -0.5f)
                verticalLineToRelative(-3.0f)
                curveToRelative(0.0f, -0.275f, -0.225f, -0.5f, -0.5f, -0.5f)
                horizontalLineToRelative(-16.0f)
                curveToRelative(-0.275f, 0.0f, -0.5f, 0.225f, -0.5f, 0.5f)
                close()
                moveTo(19.5f, 20.0f)
                horizontalLineToRelative(-16.0f)
                curveToRelative(-0.827f, 0.0f, -1.5f, -0.673f, -1.5f, -1.5f)
                verticalLineToRelative(-3.707f)
                curveToRelative(0.0f, -0.247f, 0.035f, -0.489f, 0.105f, -0.719f)
                lineTo(4.808f, 5.07f)
                curveTo(4.999f, 4.43f, 5.576f, 4.0f, 6.244f, 4.0f)
                horizontalLineToRelative(10.512f)
                curveToRelative(0.657f, 0.0f, 1.248f, 0.44f, 1.436f, 1.07f)
                lineToRelative(2.703f, 9.006f)
                curveToRelative(0.07f, 0.233f, 0.105f, 0.474f, 0.105f, 0.717f)
                lineTo(21.0f, 18.5f)
                curveToRelative(0.0f, 0.827f, -0.673f, 1.5f, -1.5f, 1.5f)
                close()
            }
        }
        .build()
        return _diskStorage!!
    }

private var _diskStorage: ImageVector? = null
