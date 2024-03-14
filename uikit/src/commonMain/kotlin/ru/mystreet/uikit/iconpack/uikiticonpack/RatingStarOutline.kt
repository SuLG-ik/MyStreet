package ru.mystreet.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.RatingStarOutline: ImageVector
    get() {
        if (_ratingStarOutline != null) {
            return _ratingStarOutline!!
        }
        _ratingStarOutline = Builder(name = "RatingStarOutline", defaultWidth = 12.0.dp,
                defaultHeight = 12.0.dp, viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEEA63A)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 1.0f)
                lineToRelative(1.5f, 3.1f)
                lineTo(11.0f, 4.6f)
                lineTo(8.5f, 7.1f)
                lineToRelative(0.6f, 3.4f)
                lineTo(6.0f, 8.9f)
                lineToRelative(-3.1f, 1.6f)
                lineToRelative(0.6f, -3.4f)
                lineTo(1.0f, 4.6f)
                lineToRelative(3.5f, -0.5f)
                lineTo(6.0f, 1.0f)
                close()
            }
        }
        .build()
        return _ratingStarOutline!!
    }

private var _ratingStarOutline: ImageVector? = null
