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

public val UIKitIconPack.AppIcon: ImageVector
    get() {
        if (_appIcon != null) {
            return _appIcon!!
        }
        _appIcon = Builder(name = "AppIcon", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 58.0f, viewportHeight = 58.0f).apply {
            path(fill = SolidColor(Color(0xFFA56A43)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(19.0f, 48.0f)
                lineToRelative(1.0f, 7.0f)
                lineToRelative(-14.0f, -1.0f)
                curveToRelative(5.0f, 0.0f, 5.0f, -6.0f, 5.0f, -12.0f)
                lineTo(11.0f, 29.32f)
                lineToRelative(4.0f, 2.38f)
                lineToRelative(3.0f, 6.3f)
                verticalLineToRelative(4.0f)
                curveToRelative(0.0f, 0.67f, 0.0f, 1.34f, 0.01f, 2.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4FBA6F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.0f, 31.7f)
                arcToRelative(8.46f, 8.46f, 0.0f, false, true, -2.78f, -1.31f)
                arcTo(8.9f, 8.9f, 0.0f, false, true, 11.0f, 29.32f)
                arcToRelative(8.426f, 8.426f, 0.0f, false, true, -1.87f, -3.04f)
                arcToRelative(6.057f, 6.057f, 0.0f, false, true, -1.42f, 0.58f)
                arcToRelative(5.653f, 5.653f, 0.0f, false, true, -1.56f, 0.22f)
                arcToRelative(6.149f, 6.149f, 0.0f, false, true, -5.47f, -8.96f)
                arcTo(6.294f, 6.294f, 0.0f, false, true, 2.5f, 16.0f)
                arcToRelative(6.294f, 6.294f, 0.0f, false, true, -1.82f, -2.12f)
                arcToRelative(6.145f, 6.145f, 0.0f, false, true, 8.33f, -8.25f)
                arcToRelative(6.271f, 6.271f, 0.0f, false, true, 2.15f, 1.88f)
                arcToRelative(8.611f, 8.611f, 0.0f, false, true, 17.15f, 1.11f)
                curveToRelative(0.0f, 0.09f, -0.01f, 0.17f, -0.01f, 0.26f)
                reflectiveCurveToRelative(-0.01f, 0.18f, -0.02f, 0.27f)
                arcToRelative(6.016f, 6.016f, 0.0f, false, true, 1.2f, -0.39f)
                arcToRelative(5.486f, 5.486f, 0.0f, false, true, 1.29f, -0.14f)
                arcToRelative(6.141f, 6.141f, 0.0f, false, true, 2.11f, 11.91f)
                curveToRelative(0.49f, 0.547f, 0.88f, 1.177f, 1.15f, 1.86f)
                arcToRelative(5.9f, 5.9f, 0.0f, false, true, 0.43f, 2.23f)
                arcToRelative(6.172f, 6.172f, 0.0f, false, true, -1.8f, 4.35f)
                curveToRelative(-0.408f, 0.404f, -0.869f, 0.75f, -1.37f, 1.03f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBDC3C7)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(23.0f, 37.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(8.0f)
                horizontalLineToRelative(-4.0f)
                close()
                moveTo(45.0f, 37.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(8.0f)
                horizontalLineToRelative(-4.0f)
                close()
                moveTo(19.0f, 47.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(8.0f)
                horizontalLineToRelative(-4.0f)
                close()
                moveTo(49.0f, 47.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(8.0f)
                horizontalLineToRelative(-4.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4FBA6F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(58.0f, 56.0f)
                arcToRelative(2.006f, 2.006f, 0.0f, false, true, -2.0f, 2.0f)
                lineTo(2.0f, 58.0f)
                curveToRelative(-0.53f, 0.003f, -1.04f, -0.21f, -1.41f, -0.59f)
                curveToRelative(-0.38f, -0.37f, -0.593f, -0.88f, -0.59f, -1.41f)
                arcToRelative(2.006f, 2.006f, 0.0f, false, true, 2.0f, -2.0f)
                horizontalLineToRelative(54.0f)
                arcToRelative(2.015f, 2.015f, 0.0f, false, true, 2.0f, 2.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFCB8252)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.0f, 44.0f)
                lineTo(56.0f, 44.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 57.0f, 45.0f)
                lineTo(57.0f, 47.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 56.0f, 48.0f)
                lineTo(16.0f, 48.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 15.0f, 47.0f)
                lineTo(15.0f, 45.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 16.0f, 44.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFCB8252)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.0f, 30.0f)
                lineTo(56.0f, 30.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 57.0f, 31.0f)
                lineTo(57.0f, 37.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 56.0f, 38.0f)
                lineTo(16.0f, 38.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 15.0f, 37.0f)
                lineTo(15.0f, 31.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 16.0f, 30.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF24AE5F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(13.0f, 23.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -4.0f, -4.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 2.0f, 0.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.0f, 2.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.0f, 2.0f)
                close()
                moveTo(24.0f, 21.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.0f, -2.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.0f, -2.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 2.0f, 0.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -4.0f, 4.0f)
                close()
            }
        }
        .build()
        return _appIcon!!
    }

private var _appIcon: ImageVector? = null
