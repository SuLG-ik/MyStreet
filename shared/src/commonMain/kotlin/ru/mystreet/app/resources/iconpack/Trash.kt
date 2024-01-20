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

public val IconPack.Trash: ImageVector
    get() {
        if (_trash != null) {
            return _trash!!
        }
        _trash = Builder(name = "Trash", defaultWidth = 21.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 21.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF1A1A1A)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(1.667f, 5.0f)
                horizontalLineTo(3.667f)
                moveTo(3.667f, 5.0f)
                horizontalLineTo(19.667f)
                moveTo(3.667f, 5.0f)
                verticalLineTo(19.0f)
                curveTo(3.667f, 19.5304f, 3.8777f, 20.0391f, 4.2528f, 20.4142f)
                curveTo(4.6279f, 20.7893f, 5.1366f, 21.0f, 5.667f, 21.0f)
                horizontalLineTo(15.667f)
                curveTo(16.1974f, 21.0f, 16.7061f, 20.7893f, 17.0812f, 20.4142f)
                curveTo(17.4563f, 20.0391f, 17.667f, 19.5304f, 17.667f, 19.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(3.667f)
                close()
                moveTo(6.667f, 5.0f)
                verticalLineTo(3.0f)
                curveTo(6.667f, 2.4696f, 6.8777f, 1.9609f, 7.2528f, 1.5858f)
                curveTo(7.6279f, 1.2107f, 8.1366f, 1.0f, 8.667f, 1.0f)
                horizontalLineTo(12.667f)
                curveTo(13.1974f, 1.0f, 13.7061f, 1.2107f, 14.0812f, 1.5858f)
                curveTo(14.4563f, 1.9609f, 14.667f, 2.4696f, 14.667f, 3.0f)
                verticalLineTo(5.0f)
                moveTo(8.667f, 10.0f)
                verticalLineTo(16.0f)
                moveTo(12.667f, 10.0f)
                verticalLineTo(16.0f)
            }
        }
        .build()
        return _trash!!
    }

private var _trash: ImageVector? = null
