package ru.mystreet.app.resources.iconpack

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
import ru.mystreet.app.resources.IconPack

public val IconPack.Follow: ImageVector
    get() {
        if (_follow != null) {
            return _follow!!
        }
        _follow = Builder(name = "Follow", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(23.873f, 11.6825f)
                horizontalLineTo(21.3076f)
                curveTo(21.051f, 7.0472f, 17.3341f, 3.3299f, 12.6984f, 3.0733f)
                verticalLineTo(0.508f)
                curveTo(12.6984f, 0.2272f, 12.4712f, 0.0f, 12.1905f, 0.0f)
                curveTo(11.9097f, 0.0f, 11.6825f, 0.2272f, 11.6825f, 0.508f)
                verticalLineTo(3.0733f)
                curveTo(7.0468f, 3.3299f, 3.3299f, 7.0472f, 3.0733f, 11.6825f)
                horizontalLineTo(0.508f)
                curveTo(0.2272f, 11.6825f, 0.0f, 11.9097f, 0.0f, 12.1905f)
                curveTo(0.0f, 12.4712f, 0.2272f, 12.6984f, 0.508f, 12.6984f)
                horizontalLineTo(3.0733f)
                curveTo(3.3299f, 17.3337f, 7.0468f, 21.051f, 11.6825f, 21.3076f)
                verticalLineTo(23.873f)
                curveTo(11.6825f, 24.1538f, 11.9097f, 24.381f, 12.1905f, 24.381f)
                curveTo(12.4712f, 24.381f, 12.6984f, 24.1538f, 12.6984f, 23.873f)
                verticalLineTo(21.3076f)
                curveTo(17.3341f, 21.051f, 21.051f, 17.3337f, 21.3076f, 12.6984f)
                horizontalLineTo(23.873f)
                curveTo(24.1538f, 12.6984f, 24.381f, 12.4712f, 24.381f, 12.1905f)
                curveTo(24.381f, 11.9097f, 24.1537f, 11.6825f, 23.873f, 11.6825f)
                close()
                moveTo(12.1905f, 20.3174f)
                curveTo(7.7093f, 20.3174f, 4.0635f, 16.6716f, 4.0635f, 12.1905f)
                curveTo(4.0635f, 7.7093f, 7.7093f, 4.0635f, 12.1905f, 4.0635f)
                curveTo(16.6716f, 4.0635f, 20.3174f, 7.7093f, 20.3174f, 12.1905f)
                curveTo(20.3174f, 16.6716f, 16.6716f, 20.3174f, 12.1905f, 20.3174f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0636f, 8.0f)
                curveTo(9.8224f, 8.0f, 8.0f, 9.823f, 8.0f, 12.0636f)
                curveTo(8.0f, 14.3042f, 9.8225f, 16.1271f, 12.0636f, 16.1271f)
                curveTo(14.3047f, 16.1271f, 16.1271f, 14.3042f, 16.1271f, 12.0636f)
                curveTo(16.1271f, 9.823f, 14.3047f, 8.0f, 12.0636f, 8.0f)
                close()
                moveTo(12.0636f, 15.1113f)
                curveTo(10.383f, 15.1113f, 9.0159f, 13.7442f, 9.0159f, 12.0636f)
                curveTo(9.0159f, 10.383f, 10.383f, 9.0159f, 12.0636f, 9.0159f)
                curveTo(13.7442f, 9.0159f, 15.1113f, 10.383f, 15.1113f, 12.0636f)
                curveTo(15.1113f, 13.7442f, 13.7442f, 15.1113f, 12.0636f, 15.1113f)
                close()
            }
        }
        .build()
        return _follow!!
    }

private var _follow: ImageVector? = null
