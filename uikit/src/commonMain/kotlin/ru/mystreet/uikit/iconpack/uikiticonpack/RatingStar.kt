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

public val UIKitIconPack.RatingStar: ImageVector
    get() {
        if (_ratingStar != null) {
            return _ratingStar!!
        }
        _ratingStar = Builder(name = "RatingStar", defaultWidth = 12.0.dp, defaultHeight = 12.0.dp,
                viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFFEEA63A)), stroke = SolidColor(Color(0xFFEEA63A)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 1.0f)
                lineTo(7.545f, 4.13f)
                lineTo(11.0f, 4.635f)
                lineTo(8.5f, 7.07f)
                lineTo(9.09f, 10.51f)
                lineTo(6.0f, 8.885f)
                lineTo(2.91f, 10.51f)
                lineTo(3.5f, 7.07f)
                lineTo(1.0f, 4.635f)
                lineTo(4.455f, 4.13f)
                lineTo(6.0f, 1.0f)
                close()
            }
        }
        .build()
        return _ratingStar!!
    }

private var _ratingStar: ImageVector? = null
