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

public val IconPack.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = Builder(name = "Search", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF1A1A1A)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(19.0f, 19.0f)
                lineTo(14.65f, 14.65f)
                moveTo(17.0f, 9.0f)
                curveTo(17.0f, 13.4183f, 13.4183f, 17.0f, 9.0f, 17.0f)
                curveTo(4.5817f, 17.0f, 1.0f, 13.4183f, 1.0f, 9.0f)
                curveTo(1.0f, 4.5817f, 4.5817f, 1.0f, 9.0f, 1.0f)
                curveTo(13.4183f, 1.0f, 17.0f, 4.5817f, 17.0f, 9.0f)
                close()
            }
        }
        .build()
        return _search!!
    }

private var _search: ImageVector? = null
