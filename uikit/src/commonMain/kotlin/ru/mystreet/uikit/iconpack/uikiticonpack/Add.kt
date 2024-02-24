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

public val UIKitIconPack.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = Builder(name = "Add", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(256.0f, 512.0f)
                arcToRelative(25.0f, 25.0f, 0.0f, false, true, -25.0f, -25.0f)
                verticalLineTo(25.0f)
                arcToRelative(25.0f, 25.0f, 0.0f, false, true, 50.0f, 0.0f)
                verticalLineToRelative(462.0f)
                arcToRelative(25.0f, 25.0f, 0.0f, false, true, -25.0f, 25.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(487.0f, 281.0f)
                horizontalLineTo(25.0f)
                arcToRelative(25.0f, 25.0f, 0.0f, false, true, 0.0f, -50.0f)
                horizontalLineToRelative(462.0f)
                arcToRelative(25.0f, 25.0f, 0.0f, false, true, 0.0f, 50.0f)
                close()
            }
        }
        .build()
        return _add!!
    }

private var _add: ImageVector? = null
