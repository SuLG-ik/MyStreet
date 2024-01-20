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

public val IconPack.Parks: ImageVector
    get() {
        if (_parks != null) {
            return _parks!!
        }
        _parks = Builder(name = "Parks", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.5518f, 13.5321f)
                curveTo(4.675f, 13.5321f, 4.796f, 13.4777f, 4.8832f, 13.3831f)
                curveTo(4.9708f, 13.2875f, 5.0201f, 13.1584f, 5.0205f, 13.0236f)
                curveTo(5.0205f, 12.8894f, 4.9703f, 12.7588f, 4.8832f, 12.6641f)
                curveTo(4.796f, 12.5695f, 4.675f, 12.5151f, 4.5518f, 12.5151f)
                curveTo(4.4285f, 12.5151f, 4.3075f, 12.5695f, 4.2203f, 12.6641f)
                curveTo(4.1327f, 12.7597f, 4.0834f, 12.8888f, 4.083f, 13.0236f)
                curveTo(4.083f, 13.1575f, 4.1332f, 13.2885f, 4.2203f, 13.3831f)
                curveTo(4.3085f, 13.4781f, 4.4275f, 13.5316f, 4.5518f, 13.5321f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(19.6863f, 13.3665f)
                curveTo(20.1231f, 13.5974f, 20.6023f, 13.7173f, 21.0986f, 13.7173f)
                curveTo(22.8821f, 13.7173f, 24.333f, 12.1434f, 24.333f, 10.2088f)
                curveTo(24.333f, 8.4538f, 23.1386f, 6.9955f, 21.5853f, 6.7401f)
                curveTo(21.6542f, 6.3725f, 21.6889f, 5.9972f, 21.6889f, 5.6186f)
                curveTo(21.6889f, 2.5205f, 19.3653f, 0.0f, 16.5092f, 0.0f)
                curveTo(13.653f, 0.0f, 11.3296f, 2.5205f, 11.3296f, 5.6186f)
                curveTo(11.3296f, 5.8004f, 11.3377f, 5.9825f, 11.3537f, 6.1637f)
                curveTo(9.5906f, 6.7085f, 8.3534f, 8.4544f, 8.3534f, 10.4867f)
                curveTo(8.3534f, 11.2035f, 8.5058f, 11.8913f, 8.7976f, 12.515f)
                horizontalLineTo(6.4268f)
                curveTo(6.1679f, 12.515f, 5.958f, 12.7426f, 5.958f, 13.0235f)
                curveTo(5.958f, 13.3042f, 6.1679f, 13.532f, 6.4268f, 13.532f)
                horizontalLineTo(11.8643f)
                verticalLineTo(15.7693f)
                horizontalLineTo(1.2705f)
                verticalLineTo(13.532f)
                horizontalLineTo(2.6768f)
                curveTo(2.9357f, 13.532f, 3.1455f, 13.3042f, 3.1455f, 13.0235f)
                curveTo(3.1455f, 12.7426f, 2.9357f, 12.515f, 2.6768f, 12.515f)
                horizontalLineTo(0.8018f)
                curveTo(0.5429f, 12.515f, 0.333f, 12.7426f, 0.333f, 13.0235f)
                verticalLineTo(16.2777f)
                curveTo(0.333f, 16.5584f, 0.5429f, 16.7862f, 0.8018f, 16.7862f)
                horizontalLineTo(2.208f)
                verticalLineTo(18.6102f)
                horizontalLineTo(0.8018f)
                curveTo(0.5429f, 18.6102f, 0.333f, 18.8378f, 0.333f, 19.1186f)
                verticalLineTo(21.1525f)
                curveTo(0.333f, 21.4334f, 0.5429f, 21.661f, 0.8018f, 21.661f)
                horizontalLineTo(2.208f)
                verticalLineTo(22.983f)
                horizontalLineTo(0.8018f)
                curveTo(0.5429f, 22.983f, 0.333f, 23.2107f, 0.333f, 23.4915f)
                curveTo(0.333f, 23.7724f, 0.5429f, 24.0f, 0.8018f, 24.0f)
                horizontalLineTo(21.333f)
                curveTo(21.5919f, 24.0f, 21.8018f, 23.7724f, 21.8018f, 23.4915f)
                curveTo(21.8018f, 23.2107f, 21.5919f, 22.983f, 21.333f, 22.983f)
                horizontalLineTo(21.2856f)
                curveTo(21.2883f, 22.9286f, 21.2902f, 22.874f, 21.2902f, 22.8194f)
                curveTo(21.2902f, 21.2133f, 20.0857f, 19.9068f, 18.6051f, 19.9068f)
                curveTo(18.0117f, 19.9068f, 17.4409f, 20.1229f, 16.978f, 20.5053f)
                verticalLineTo(15.5069f)
                curveTo(18.1539f, 15.3343f, 19.1769f, 14.5223f, 19.6863f, 13.3665f)
                close()
                moveTo(9.8915f, 12.515f)
                curveTo(9.4982f, 11.9219f, 9.2909f, 11.2238f, 9.2909f, 10.4867f)
                curveTo(9.2909f, 8.7849f, 10.4119f, 7.3423f, 11.9564f, 7.0567f)
                curveTo(12.0176f, 7.0453f, 12.0761f, 7.0209f, 12.1286f, 6.9848f)
                curveTo(12.1811f, 6.9488f, 12.2264f, 6.9017f, 12.262f, 6.8465f)
                curveTo(12.2975f, 6.7912f, 12.3226f, 6.7289f, 12.3357f, 6.663f)
                curveTo(12.3489f, 6.5971f, 12.3498f, 6.5291f, 12.3385f, 6.4628f)
                curveTo(12.291f, 6.1844f, 12.2671f, 5.9018f, 12.2671f, 5.6186f)
                curveTo(12.2671f, 3.0812f, 14.1701f, 1.017f, 16.5093f, 1.017f)
                curveTo(18.8483f, 1.017f, 20.7515f, 3.0812f, 20.7515f, 5.6186f)
                curveTo(20.7519f, 6.106f, 20.6808f, 6.5904f, 20.5409f, 7.0535f)
                curveTo(20.5173f, 7.1315f, 20.5115f, 7.2145f, 20.5239f, 7.2955f)
                curveTo(20.5363f, 7.3765f, 20.5667f, 7.453f, 20.6123f, 7.5185f)
                curveTo(20.6579f, 7.584f, 20.7174f, 7.6365f, 20.7858f, 7.6716f)
                curveTo(20.8542f, 7.7067f, 20.9295f, 7.7234f, 21.0051f, 7.7201f)
                curveTo(21.036f, 7.7187f, 21.0671f, 7.7173f, 21.0986f, 7.7173f)
                curveTo(22.3652f, 7.7173f, 23.3955f, 8.8349f, 23.3955f, 10.2088f)
                curveTo(23.3955f, 11.5827f, 22.3652f, 12.7003f, 21.0986f, 12.7003f)
                curveTo(20.6f, 12.7003f, 20.126f, 12.5301f, 19.7275f, 12.208f)
                curveTo(19.6665f, 12.1586f, 19.5954f, 12.126f, 19.5202f, 12.1132f)
                curveTo(19.445f, 12.1003f, 19.368f, 12.1076f, 19.296f, 12.1342f)
                curveTo(19.2239f, 12.1609f, 19.1589f, 12.2062f, 19.1066f, 12.2662f)
                curveTo(19.0543f, 12.3262f, 19.0163f, 12.3991f, 18.9959f, 12.4787f)
                curveTo(18.7285f, 13.5171f, 17.9318f, 14.285f, 16.978f, 14.4772f)
                verticalLineTo(11.1233f)
                lineTo(19.3046f, 8.5994f)
                curveTo(19.4877f, 8.4008f, 19.4877f, 8.0788f, 19.3046f, 7.8802f)
                curveTo(19.1216f, 7.6817f, 18.8248f, 7.6817f, 18.6417f, 7.8802f)
                lineTo(16.978f, 9.6851f)
                verticalLineTo(7.65f)
                curveTo(16.978f, 7.3691f, 16.768f, 7.1415f, 16.5093f, 7.1415f)
                curveTo(16.2504f, 7.1415f, 16.0405f, 7.3691f, 16.0405f, 7.65f)
                verticalLineTo(11.5118f)
                lineTo(14.2243f, 9.5416f)
                curveTo(14.0412f, 9.343f, 13.7444f, 9.343f, 13.5613f, 9.5416f)
                curveTo(13.3782f, 9.7401f, 13.3782f, 10.0621f, 13.5613f, 10.2607f)
                lineTo(16.0116f, 12.9188f)
                curveTo(16.0209f, 12.9288f, 16.0306f, 12.9379f, 16.0404f, 12.9468f)
                verticalLineTo(14.4782f)
                curveTo(15.4987f, 14.3718f, 15.0056f, 14.081f, 14.62f, 13.6323f)
                curveTo(14.5488f, 13.5493f, 14.454f, 13.4945f, 14.3507f, 13.4767f)
                curveTo(14.2475f, 13.4589f, 14.1417f, 13.479f, 14.0502f, 13.534f)
                curveTo(13.6648f, 13.7647f, 13.2398f, 13.9069f, 12.8016f, 13.9517f)
                verticalLineTo(13.0233f)
                curveTo(12.8016f, 12.7425f, 12.5918f, 12.5148f, 12.3328f, 12.5148f)
                horizontalLineTo(9.8915f)
                verticalLineTo(12.515f)
                close()
                moveTo(12.333f, 16.7862f)
                curveTo(12.5919f, 16.7862f, 12.8018f, 16.5584f, 12.8018f, 16.2777f)
                verticalLineTo(14.972f)
                curveTo(13.281f, 14.9339f, 13.7505f, 14.8061f, 14.1895f, 14.594f)
                curveTo(14.7108f, 15.0884f, 15.3544f, 15.4058f, 16.0405f, 15.5071f)
                verticalLineTo(20.8637f)
                curveTo(14.9835f, 20.9964f, 14.1304f, 21.8646f, 13.9288f, 22.983f)
                horizontalLineTo(10.9268f)
                verticalLineTo(21.661f)
                horizontalLineTo(12.333f)
                curveTo(12.5919f, 21.661f, 12.8018f, 21.4334f, 12.8018f, 21.1525f)
                verticalLineTo(19.1186f)
                curveTo(12.8018f, 18.8378f, 12.5919f, 18.6102f, 12.333f, 18.6102f)
                horizontalLineTo(10.9268f)
                verticalLineTo(16.7862f)
                horizontalLineTo(12.333f)
                close()
                moveTo(3.1455f, 16.7862f)
                horizontalLineTo(9.9893f)
                verticalLineTo(18.6102f)
                horizontalLineTo(3.1455f)
                verticalLineTo(16.7862f)
                close()
                moveTo(1.2705f, 19.6271f)
                horizontalLineTo(11.8643f)
                verticalLineTo(20.6441f)
                horizontalLineTo(1.2705f)
                verticalLineTo(19.6271f)
                close()
                moveTo(3.1455f, 21.661f)
                horizontalLineTo(9.9893f)
                verticalLineTo(22.983f)
                horizontalLineTo(3.1455f)
                verticalLineTo(21.661f)
                close()
                moveTo(17.1849f, 21.7152f)
                curveTo(17.5133f, 21.2197f, 18.0441f, 20.9237f, 18.6051f, 20.9237f)
                curveTo(19.5686f, 20.9237f, 20.3526f, 21.7743f, 20.3526f, 22.8194f)
                curveTo(20.3526f, 22.874f, 20.3505f, 22.9286f, 20.3461f, 22.983f)
                horizontalLineTo(14.8956f)
                curveTo(15.0929f, 22.333f, 15.658f, 21.8624f, 16.3225f, 21.8624f)
                curveTo(16.4465f, 21.8624f, 16.5701f, 21.8792f, 16.69f, 21.9117f)
                curveTo(16.8764f, 21.9625f, 17.0727f, 21.8846f, 17.1849f, 21.7152f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.5088f, 6.1244f)
                curveTo(16.6325f, 6.1244f, 16.753f, 6.07f, 16.8402f, 5.9754f)
                curveTo(16.9274f, 5.8809f, 16.9775f, 5.7496f, 16.9775f, 5.616f)
                curveTo(16.9775f, 5.4822f, 16.9274f, 5.3509f, 16.8402f, 5.2564f)
                curveTo(16.753f, 5.1619f, 16.6325f, 5.1074f, 16.5088f, 5.1074f)
                curveTo(16.3855f, 5.1074f, 16.2645f, 5.1618f, 16.1774f, 5.2564f)
                curveTo(16.0902f, 5.351f, 16.04f, 5.4822f, 16.04f, 5.6159f)
                curveTo(16.04f, 5.7496f, 16.0902f, 5.8809f, 16.1774f, 5.9754f)
                curveTo(16.2646f, 6.0699f, 16.3855f, 6.1244f, 16.5088f, 6.1244f)
                close()
            }
        }
        .build()
        return _parks!!
    }

private var _parks: ImageVector? = null
