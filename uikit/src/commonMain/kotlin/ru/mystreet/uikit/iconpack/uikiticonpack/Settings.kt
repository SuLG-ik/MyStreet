package ru.mystreet.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(name = "Settings", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 682.667f, viewportHeight = 682.667f).apply {
            group {
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 30.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType =
                        NonZero) {
                    moveTo(405.599f, 92.376f)
                    arcToRelative(255.71f, 255.71f, 135.0f, false, true, 66.292f, 27.517f)
                    lineToRelative(20.917f, -20.92f)
                    arcToRelative(42.828f, 42.828f, 135.0f, false, true, 30.303f, -12.553f)
                    arcToRelative(42.821f, 42.821f, 135.0f, false, true, 30.291f, 12.553f)
                    lineToRelative(30.291f, 30.292f)
                    arcToRelative(42.813f, 42.813f, 45.0f, false, true, 12.553f, 30.291f)
                    arcToRelative(42.817f, 42.817f, 0.0f, false, true, -12.553f, 30.301f)
                    lineTo(562.773f, 210.776f)
                    arcToRelative(255.783f, 255.783f, 135.0f, false, true, 27.517f, 66.292f)
                    lineToRelative(29.529f, -0.0f)
                    curveToRelative(23.661f, -0.0f, 42.845f, 19.184f, 42.845f, 42.844f)
                    lineToRelative(0.0f, 42.845f)
                    curveToRelative(0.0f, 23.66f, -19.184f, 42.844f, -42.845f, 42.844f)
                    lineToRelative(-29.529f, -0.0f)
                    arcToRelative(255.774f, 255.774f, 45.0f, false, true, -27.517f, 66.291f)
                    lineToRelative(20.919f, 20.919f)
                    arcToRelative(42.823f, 42.823f, 135.0f, false, true, 12.553f, 30.303f)
                    arcToRelative(42.813f, 42.813f, 135.0f, false, true, -12.553f, 30.291f)
                    lineToRelative(-30.291f, 30.291f)
                    arcToRelative(42.816f, 42.816f, 0.0f, false, true, -30.291f, 12.553f)
                    arcToRelative(42.823f, 42.823f, 45.0f, false, true, -30.303f, -12.553f)
                    lineTo(471.891f, 562.775f)
                    arcTo(255.706f, 255.706f, 135.0f, false, true, 405.599f, 590.291f)
                    lineToRelative(0.0f, 29.531f)
                    curveToRelative(0.0f, 23.661f, -19.184f, 42.845f, -42.845f, 42.845f)
                    lineToRelative(-42.844f, -0.0f)
                    curveToRelative(-23.661f, -0.0f, -42.844f, -19.184f, -42.844f, -42.845f)
                    lineToRelative(0.0f, -29.531f)
                    arcToRelative(255.706f, 255.706f, 135.0f, false, true, -66.292f, -27.516f)
                    lineToRelative(-20.919f, 20.919f)
                    arcToRelative(42.82f, 42.82f, 135.0f, false, true, -30.301f, 12.553f)
                    arcToRelative(42.811f, 42.811f, 0.0f, false, true, -30.291f, -12.553f)
                    lineToRelative(-30.292f, -30.291f)
                    arcToRelative(42.819f, 42.819f, 0.0f, false, true, -12.553f, -30.291f)
                    arcToRelative(42.828f, 42.828f, 45.0f, false, true, 12.553f, -30.303f)
                    lineToRelative(20.92f, -20.919f)
                    arcToRelative(255.727f, 255.727f, 0.0f, false, true, -27.517f, -66.291f)
                    lineToRelative(-29.529f, -0.0f)
                    curveToRelative(-23.661f, -0.0f, -42.845f, -19.184f, -42.845f, -42.844f)
                    lineToRelative(0.0f, -42.845f)
                    curveToRelative(0.0f, -23.66f, 19.184f, -42.844f, 42.845f, -42.844f)
                    lineToRelative(29.529f, -0.0f)
                    arcTo(255.737f, 255.737f, 45.0f, false, true, 119.892f, 210.776f)
                    lineToRelative(-20.92f, -20.919f)
                    arcToRelative(42.823f, 42.823f, 135.0f, false, true, -12.553f, -30.301f)
                    arcToRelative(42.819f, 42.819f, 45.0f, false, true, 12.553f, -30.291f)
                    lineToRelative(30.292f, -30.292f)
                    arcToRelative(42.816f, 42.816f, 45.0f, false, true, 30.291f, -12.553f)
                    arcToRelative(42.825f, 42.825f, 0.0f, false, true, 30.301f, 12.553f)
                    lineToRelative(20.919f, 20.92f)
                    arcTo(255.71f, 255.71f, 45.0f, false, true, 277.066f, 92.376f)
                    lineToRelative(0.0f, -29.531f)
                    curveToRelative(0.0f, -23.661f, 19.183f, -42.844f, 42.844f, -42.844f)
                    lineToRelative(42.844f, -0.0f)
                    curveTo(386.415f, 20.002f, 405.599f, 39.185f, 405.599f, 62.846f)
                    close()
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 30.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType =
                        NonZero) {
                    moveTo(341.332f, 212.801f)
                    curveToRelative(70.94f, -0.0f, 128.533f, 57.593f, 128.533f, 128.533f)
                    curveToRelative(0.0f, 70.938f, -57.593f, 128.533f, -128.533f, 128.533f)
                    curveToRelative(-70.94f, -0.0f, -128.533f, -57.595f, -128.533f, -128.533f)
                    curveTo(212.799f, 270.395f, 270.393f, 212.801f, 341.332f, 212.801f)
                    close()
                }
            }
        }
        .build()
        return _settings!!
    }

private var _settings: ImageVector? = null
