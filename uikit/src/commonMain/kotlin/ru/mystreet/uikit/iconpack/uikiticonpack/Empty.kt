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

public val UIKitIconPack.Empty: ImageVector
    get() {
        if (_empty != null) {
            return _empty!!
        }
        _empty = Builder(name = "Empty", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(21.5f, 22.0f)
                horizontalLineToRelative(-19.0f)
                arcTo(2.503f, 2.503f, 0.0f, false, true, 0.0f, 19.5f)
                verticalLineToRelative(-7.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.044f, -0.205f)
                lineToRelative(3.969f, -8.82f)
                arcTo(2.506f, 2.506f, 0.0f, false, true, 6.293f, 2.0f)
                horizontalLineToRelative(11.414f)
                curveToRelative(0.981f, 0.0f, 1.876f, 0.579f, 2.28f, 1.475f)
                lineToRelative(3.969f, 8.82f)
                arcTo(0.5f, 0.5f, 0.0f, false, true, 24.0f, 12.5f)
                verticalLineToRelative(7.0f)
                curveToRelative(0.0f, 1.379f, -1.122f, 2.5f, -2.5f, 2.5f)
                close()
                moveTo(1.0f, 12.607f)
                verticalLineTo(19.5f)
                curveToRelative(0.0f, 0.827f, 0.673f, 1.5f, 1.5f, 1.5f)
                horizontalLineToRelative(19.0f)
                curveToRelative(0.827f, 0.0f, 1.5f, -0.673f, 1.5f, -1.5f)
                verticalLineToRelative(-6.893f)
                lineToRelative(-3.925f, -8.723f)
                arcTo(1.505f, 1.505f, 0.0f, false, false, 17.707f, 3.0f)
                horizontalLineTo(6.293f)
                curveToRelative(-0.589f, 0.0f, -1.126f, 0.348f, -1.368f, 0.885f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.807f, 17.0f)
                horizontalLineTo(7.193f)
                arcToRelative(1.506f, 1.506f, 0.0f, false, true, -1.404f, -0.973f)
                lineToRelative(-1.014f, -2.703f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 4.307f, 13.0f)
                horizontalLineTo(0.75f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.0f, -1.0f)
                horizontalLineToRelative(3.557f)
                curveToRelative(0.622f, 0.0f, 1.186f, 0.391f, 1.405f, 0.973f)
                lineToRelative(1.013f, 2.703f)
                arcToRelative(0.502f, 0.502f, 0.0f, false, false, 0.468f, 0.324f)
                horizontalLineToRelative(9.613f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.468f, -0.324f)
                lineToRelative(1.013f, -2.703f)
                arcTo(1.51f, 1.51f, 0.0f, false, true, 19.693f, 12.0f)
                horizontalLineTo(23.5f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.0f, 1.0f)
                horizontalLineToRelative(-3.807f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.468f, 0.324f)
                lineToRelative(-1.013f, 2.703f)
                arcToRelative(1.509f, 1.509f, 0.0f, false, true, -1.405f, 0.973f)
                close()
            }
        }
        .build()
        return _empty!!
    }

private var _empty: ImageVector? = null
