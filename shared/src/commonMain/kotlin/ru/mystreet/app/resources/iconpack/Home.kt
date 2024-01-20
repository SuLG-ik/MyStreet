package ru.mystreet.app.resources.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.app.resources.IconPack

public val IconPack.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(name = "Home", defaultWidth = 20.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 20.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF1A1A1A)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.0f, 21.0f)
                verticalLineTo(11.0f)
                horizontalLineTo(13.0f)
                verticalLineTo(21.0f)
                moveTo(1.0f, 8.0f)
                lineTo(10.0f, 1.0f)
                lineTo(19.0f, 8.0f)
                verticalLineTo(19.0f)
                curveTo(19.0f, 19.5304f, 18.7893f, 20.0391f, 18.4142f, 20.4142f)
                curveTo(18.0391f, 20.7893f, 17.5304f, 21.0f, 17.0f, 21.0f)
                horizontalLineTo(3.0f)
                curveTo(2.4696f, 21.0f, 1.9609f, 20.7893f, 1.5858f, 20.4142f)
                curveTo(1.2107f, 20.0391f, 1.0f, 19.5304f, 1.0f, 19.0f)
                verticalLineTo(8.0f)
                close()
            }
        }
        .build()
        return _home!!
    }

private var _home: ImageVector? = null
