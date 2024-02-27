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

public val UIKitIconPack.Accept: ImageVector
    get() {
        if (_accept != null) {
            return _accept!!
        }
        _accept = Builder(name = "Accept", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(34.192f, 82.665f)
                curveToRelative(-0.536f, 0.0f, -1.05f, -0.213f, -1.43f, -0.592f)
                lineToRelative(-29.67f, -29.67f)
                arcToRelative(2.02f, 2.02f, 0.0f, true, true, 2.858f, -2.859f)
                lineToRelative(28.242f, 28.242f)
                lineTo(94.05f, 17.927f)
                arcToRelative(2.02f, 2.02f, 0.0f, true, true, 2.858f, 2.858f)
                lineTo(35.62f, 82.073f)
                arcToRelative(2.022f, 2.022f, 0.0f, false, true, -1.429f, 0.592f)
                close()
            }
        }
        .build()
        return _accept!!
    }

private var _accept: ImageVector? = null
