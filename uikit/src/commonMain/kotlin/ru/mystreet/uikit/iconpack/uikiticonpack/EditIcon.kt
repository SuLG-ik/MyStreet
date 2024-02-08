package ru.mystreet.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.EditIcon: ImageVector
    get() {
        if (_editIcon != null) {
            return _editIcon!!
        }
        _editIcon = Builder(name = "EditIcon", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 401.523f, viewportHeight = 401.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(370.59f, 250.973f)
                curveToRelative(-5.524f, 0.0f, -10.0f, 4.476f, -10.0f, 10.0f)
                verticalLineToRelative(88.789f)
                curveToRelative(-0.02f, 16.562f, -13.438f, 29.984f, -30.0f, 30.0f)
                lineTo(50.0f, 379.762f)
                curveToRelative(-16.563f, -0.016f, -29.98f, -13.438f, -30.0f, -30.0f)
                lineTo(20.0f, 89.172f)
                curveToRelative(0.02f, -16.559f, 13.438f, -29.98f, 30.0f, -30.0f)
                horizontalLineToRelative(88.79f)
                curveToRelative(5.523f, 0.0f, 10.0f, -4.477f, 10.0f, -10.0f)
                curveToRelative(0.0f, -5.52f, -4.477f, -10.0f, -10.0f, -10.0f)
                lineTo(50.0f, 39.172f)
                curveToRelative(-27.602f, 0.031f, -49.969f, 22.398f, -50.0f, 50.0f)
                verticalLineToRelative(260.594f)
                curveToRelative(0.031f, 27.601f, 22.398f, 49.968f, 50.0f, 50.0f)
                horizontalLineToRelative(280.59f)
                curveToRelative(27.601f, -0.032f, 49.969f, -22.399f, 50.0f, -50.0f)
                verticalLineToRelative(-88.793f)
                curveToRelative(0.0f, -5.524f, -4.477f, -10.0f, -10.0f, -10.0f)
                close()
                moveTo(370.59f, 250.973f)
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(376.629f, 13.441f)
                curveToRelative(-17.574f, -17.574f, -46.067f, -17.574f, -63.64f, 0.0f)
                lineTo(134.581f, 191.848f)
                arcToRelative(9.997f, 9.997f, 0.0f, false, false, -2.566f, 4.402f)
                lineToRelative(-23.461f, 84.7f)
                arcToRelative(9.997f, 9.997f, 0.0f, false, false, 12.304f, 12.308f)
                lineToRelative(84.7f, -23.465f)
                arcToRelative(9.997f, 9.997f, 0.0f, false, false, 4.402f, -2.566f)
                lineToRelative(178.402f, -178.41f)
                curveToRelative(17.547f, -17.587f, 17.547f, -46.055f, 0.0f, -63.641f)
                close()
                moveTo(156.37f, 198.348f)
                lineTo(302.383f, 52.332f)
                lineToRelative(47.09f, 47.09f)
                lineToRelative(-146.016f, 146.016f)
                close()
                moveTo(146.964f, 217.223f)
                lineTo(184.584f, 254.848f)
                lineTo(132.546f, 269.266f)
                close()
                moveTo(374.223f, 74.676f)
                lineTo(363.617f, 85.28f)
                lineToRelative(-47.094f, -47.094f)
                lineToRelative(10.61f, -10.605f)
                curveToRelative(9.762f, -9.762f, 25.59f, -9.762f, 35.351f, 0.0f)
                lineToRelative(11.739f, 11.734f)
                curveToRelative(9.746f, 9.774f, 9.746f, 25.59f, 0.0f, 35.36f)
                close()
                moveTo(374.223f, 74.676f)
            }
        }
        .build()
        return _editIcon!!
    }

private var _editIcon: ImageVector? = null
