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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.Playground: ImageVector
    get() {
        if (_playground != null) {
            return _playground!!
        }
        _playground = Builder(name = "Playground", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.8429f, 16.5177f)
                curveTo(14.691f, 16.5177f, 14.5513f, 16.4188f, 14.5058f, 16.266f)
                curveTo(14.4505f, 16.0799f, 14.5569f, 15.8845f, 14.743f, 15.8291f)
                lineTo(20.5855f, 14.1004f)
                curveTo(20.7716f, 14.0456f, 20.9671f, 14.1515f, 21.0224f, 14.3376f)
                curveTo(21.0777f, 14.5237f, 20.9713f, 14.7191f, 20.7852f, 14.7745f)
                lineTo(14.9427f, 16.5032f)
                curveTo(14.9094f, 16.5131f, 14.8757f, 16.5177f, 14.8429f, 16.5177f)
                close()
            }
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(13.1788f, 14.7507f)
                    curveTo(13.0269f, 14.7507f, 12.8872f, 14.6518f, 12.8418f, 14.499f)
                    curveTo(12.7865f, 14.3129f, 12.8929f, 14.1174f, 13.079f, 14.0621f)
                    lineTo(21.7237f, 11.5041f)
                    curveTo(21.8554f, 11.4652f, 21.9922f, 11.4451f, 22.131f, 11.4451f)
                    curveTo(22.7615f, 11.4451f, 23.3272f, 11.8679f, 23.5068f, 12.473f)
                    curveTo(23.6155f, 12.841f, 23.5747f, 13.2287f, 23.3915f, 13.5657f)
                    curveTo(23.2082f, 13.9023f, 22.9054f, 14.1479f, 22.5374f, 14.2566f)
                    lineTo(22.124f, 14.379f)
                    curveTo(21.9374f, 14.4343f, 21.7424f, 14.3279f, 21.6871f, 14.1418f)
                    curveTo(21.6318f, 13.9557f, 21.7382f, 13.7602f, 21.9243f, 13.7049f)
                    lineTo(22.3377f, 13.5826f)
                    curveTo(22.5252f, 13.5273f, 22.6799f, 13.4021f, 22.7732f, 13.2301f)
                    curveTo(22.8665f, 13.0585f, 22.8876f, 12.8607f, 22.8318f, 12.6732f)
                    curveTo(22.7404f, 12.3648f, 22.4516f, 12.1487f, 22.1301f, 12.1487f)
                    curveTo(22.0593f, 12.1487f, 21.9894f, 12.1585f, 21.9229f, 12.1787f)
                    lineTo(13.2777f, 14.7366f)
                    curveTo(13.2444f, 14.7465f, 13.2107f, 14.7512f, 13.1779f, 14.7512f)
                    lineTo(13.1788f, 14.7507f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(1.868f, 20.3109f)
                    curveTo(1.2375f, 20.3109f, 0.6722f, 19.8881f, 0.4927f, 19.283f)
                    curveTo(0.2682f, 18.5241f, 0.7032f, 17.7239f, 1.4616f, 17.4998f)
                    lineTo(9.3999f, 15.1509f)
                    curveTo(9.586f, 15.0961f, 9.7815f, 15.202f, 9.8368f, 15.3881f)
                    curveTo(9.8921f, 15.5742f, 9.7857f, 15.7697f, 9.5996f, 15.825f)
                    lineTo(1.6613f, 18.1739f)
                    curveTo(1.2741f, 18.2883f, 1.0524f, 18.6966f, 1.1668f, 19.0833f)
                    curveTo(1.2582f, 19.3917f, 1.5465f, 19.6078f, 1.868f, 19.6078f)
                    curveTo(1.9379f, 19.6078f, 2.0077f, 19.5975f, 2.0761f, 19.5773f)
                    lineTo(8.8838f, 17.5631f)
                    curveTo(9.0704f, 17.5083f, 9.2654f, 17.6142f, 9.3207f, 17.8003f)
                    curveTo(9.376f, 17.9864f, 9.2696f, 18.1819f, 9.0835f, 18.2372f)
                    lineTo(2.2763f, 20.2514f)
                    curveTo(2.1436f, 20.2908f, 2.0063f, 20.3109f, 1.8685f, 20.3109f)
                    horizontalLineTo(1.868f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(17.7704f, 13.3922f)
                    curveTo(17.7122f, 13.3922f, 17.6546f, 13.3776f, 17.6026f, 13.3495f)
                    curveTo(17.5205f, 13.305f, 17.4596f, 13.23f, 17.4333f, 13.1404f)
                    lineTo(16.6097f, 10.357f)
                    curveTo(16.4415f, 9.7879f, 16.7672f, 9.1879f, 17.3363f, 9.0197f)
                    curveTo(17.9054f, 8.8514f, 18.5054f, 9.1772f, 18.6741f, 9.7462f)
                    lineTo(19.4977f, 12.5297f)
                    curveTo(19.553f, 12.7158f, 19.4466f, 12.9112f, 19.2605f, 12.9665f)
                    lineTo(17.8702f, 13.3781f)
                    curveTo(17.8374f, 13.3879f, 17.8041f, 13.3926f, 17.7704f, 13.3926f)
                    verticalLineTo(13.3922f)
                    close()
                    moveTo(17.6415f, 9.6783f)
                    curveTo(17.6063f, 9.6783f, 17.5712f, 9.6834f, 17.5355f, 9.6937f)
                    curveTo(17.3382f, 9.7523f, 17.2252f, 9.96f, 17.2833f, 10.1578f)
                    lineTo(18.0071f, 12.6042f)
                    lineTo(18.7229f, 12.3923f)
                    lineTo(17.9991f, 9.9459f)
                    curveTo(17.9513f, 9.7837f, 17.8022f, 9.6787f, 17.641f, 9.6787f)
                    lineTo(17.6415f, 9.6783f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(4.2249f, 17.4004f)
                    curveTo(4.1668f, 17.4004f, 4.1092f, 17.3859f, 4.0571f, 17.3578f)
                    curveTo(3.9751f, 17.3132f, 3.9142f, 17.2383f, 3.8879f, 17.1487f)
                    lineTo(3.0643f, 14.3653f)
                    curveTo(2.9828f, 14.0897f, 3.0132f, 13.7986f, 3.1506f, 13.5459f)
                    curveTo(3.2879f, 13.2933f, 3.5153f, 13.109f, 3.7909f, 13.0275f)
                    curveTo(4.3599f, 12.8592f, 4.9599f, 13.185f, 5.1282f, 13.754f)
                    lineTo(5.9518f, 16.5375f)
                    curveTo(6.0071f, 16.7236f, 5.9007f, 16.919f, 5.7146f, 16.9743f)
                    lineTo(4.3243f, 17.3859f)
                    curveTo(4.2915f, 17.3958f, 4.2582f, 17.4004f, 4.2245f, 17.4004f)
                    horizontalLineTo(4.2249f)
                    close()
                    moveTo(4.096f, 13.6865f)
                    curveTo(4.0609f, 13.6865f, 4.0257f, 13.6917f, 3.9901f, 13.702f)
                    curveTo(3.8945f, 13.7301f, 3.8157f, 13.7943f, 3.7679f, 13.8815f)
                    curveTo(3.7201f, 13.9692f, 3.7098f, 14.07f, 3.7379f, 14.1656f)
                    lineTo(4.4617f, 16.612f)
                    lineTo(5.1774f, 16.4001f)
                    lineTo(4.4537f, 13.9537f)
                    curveTo(4.4059f, 13.7915f, 4.2568f, 13.6865f, 4.0956f, 13.6865f)
                    horizontalLineTo(4.096f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(15.0167f, 21.8334f)
                    horizontalLineTo(8.9844f)
                    curveTo(8.7903f, 21.8334f, 8.6328f, 21.6759f, 8.6328f, 21.4818f)
                    verticalLineTo(17.1763f)
                    curveTo(8.6328f, 15.3191f, 10.1436f, 13.8083f, 12.0008f, 13.8083f)
                    curveTo(13.858f, 13.8083f, 15.3687f, 15.3191f, 15.3687f, 17.1763f)
                    verticalLineTo(21.4818f)
                    curveTo(15.3687f, 21.6759f, 15.2112f, 21.8334f, 15.0172f, 21.8334f)
                    horizontalLineTo(15.0167f)
                    close()
                    moveTo(9.3359f, 21.1302f)
                    horizontalLineTo(14.6652f)
                    verticalLineTo(17.1763f)
                    curveTo(14.6652f, 15.7068f, 13.4698f, 14.5115f, 12.0003f, 14.5115f)
                    curveTo(10.5308f, 14.5115f, 9.3355f, 15.7068f, 9.3355f, 17.1763f)
                    verticalLineTo(21.1302f)
                    horizontalLineTo(9.3359f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(12.0f, 18.9465f)
                    curveTo(11.024f, 18.9465f, 10.2295f, 18.1524f, 10.2295f, 17.176f)
                    curveTo(10.2295f, 16.1996f, 11.0236f, 15.4055f, 12.0f, 15.4055f)
                    curveTo(12.9764f, 15.4055f, 13.7704f, 16.1996f, 13.7704f, 17.176f)
                    curveTo(13.7704f, 18.1524f, 12.9764f, 18.9465f, 12.0f, 18.9465f)
                    close()
                    moveTo(12.0f, 16.1091f)
                    curveTo(11.4117f, 16.1091f, 10.9326f, 16.5877f, 10.9326f, 17.1765f)
                    curveTo(10.9326f, 17.7652f, 11.4112f, 18.2438f, 12.0f, 18.2438f)
                    curveTo(12.5887f, 18.2438f, 13.0673f, 17.7652f, 13.0673f, 17.1765f)
                    curveTo(13.0673f, 16.5877f, 12.5887f, 16.1091f, 12.0f, 16.1091f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(21.0439f, 21.8335f)
                    curveTo(20.8498f, 21.8335f, 20.6923f, 21.676f, 20.6923f, 21.4819f)
                    curveTo(20.6923f, 20.5271f, 19.9264f, 19.7251f, 19.9184f, 19.7171f)
                    curveTo(19.7834f, 19.5779f, 19.7867f, 19.3552f, 19.9264f, 19.2202f)
                    curveTo(20.0661f, 19.0852f, 20.2882f, 19.0885f, 20.4232f, 19.2282f)
                    curveTo(20.442f, 19.2479f, 20.6632f, 19.479f, 20.8897f, 19.8493f)
                    curveTo(21.0401f, 19.314f, 21.2764f, 18.8804f, 21.597f, 18.5532f)
                    curveTo(21.7329f, 18.4144f, 21.9556f, 18.4126f, 22.0943f, 18.548f)
                    curveTo(22.2331f, 18.684f, 22.2354f, 18.9066f, 22.0995f, 19.0454f)
                    curveTo(21.7784f, 19.373f, 21.3954f, 20.0621f, 21.3954f, 21.4824f)
                    curveTo(21.3954f, 21.6765f, 21.2379f, 21.834f, 21.0439f, 21.834f)
                    verticalLineTo(21.8335f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(22.5652f, 24.0001f)
                    horizontalLineTo(1.4348f)
                    curveTo(1.0519f, 24.0001f, 0.6914f, 23.851f, 0.4205f, 23.5796f)
                    curveTo(0.1528f, 23.3129f, 0.0f, 22.943f, 0.0f, 22.5647f)
                    curveTo(0.0f, 21.774f, 0.6436f, 21.1304f, 1.4348f, 21.1304f)
                    horizontalLineTo(3.3412f)
                    curveTo(3.5353f, 21.1304f, 3.6928f, 21.2879f, 3.6928f, 21.4819f)
                    curveTo(3.6928f, 21.676f, 3.5353f, 21.8335f, 3.3412f, 21.8335f)
                    horizontalLineTo(1.4348f)
                    curveTo(1.0313f, 21.8335f, 0.7031f, 22.1616f, 0.7031f, 22.5652f)
                    curveTo(0.7031f, 22.7579f, 0.7809f, 22.9463f, 0.9173f, 23.0822f)
                    curveTo(1.0561f, 23.221f, 1.2398f, 23.2969f, 1.4348f, 23.2969f)
                    horizontalLineTo(22.5652f)
                    curveTo(22.9688f, 23.2969f, 23.2969f, 22.9688f, 23.2969f, 22.5652f)
                    curveTo(23.2969f, 22.3726f, 23.2191f, 22.1841f, 23.0831f, 22.0482f)
                    curveTo(22.9448f, 21.9094f, 22.7611f, 21.8335f, 22.5652f, 21.8335f)
                    horizontalLineTo(4.7475f)
                    curveTo(4.5534f, 21.8335f, 4.3959f, 21.676f, 4.3959f, 21.4819f)
                    curveTo(4.3959f, 21.2879f, 4.5534f, 21.1304f, 4.7475f, 21.1304f)
                    horizontalLineTo(22.5652f)
                    curveTo(22.9491f, 21.1304f, 23.3095f, 21.2799f, 23.58f, 21.5508f)
                    curveTo(23.8472f, 21.8176f, 24.0005f, 22.1874f, 24.0f, 22.5657f)
                    curveTo(24.0f, 23.3565f, 23.3564f, 24.0001f, 22.5652f, 24.0001f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(21.5171f, 4.9659f)
                    horizontalLineTo(18.968f)
                    curveTo(17.9377f, 4.9659f, 17.0996f, 4.1278f, 17.0996f, 3.0975f)
                    curveTo(17.0996f, 2.0672f, 17.9377f, 1.2291f, 18.968f, 1.2291f)
                    curveTo(19.0979f, 1.2291f, 19.2259f, 1.2422f, 19.351f, 1.2684f)
                    curveTo(19.7865f, 0.4884f, 20.6054f, 0.0f, 21.5171f, 0.0f)
                    curveTo(22.8863f, 0.0f, 24.0001f, 1.1137f, 24.0001f, 2.483f)
                    curveTo(24.0001f, 3.8522f, 22.8863f, 4.9659f, 21.5171f, 4.9659f)
                    close()
                    moveTo(18.968f, 1.9322f)
                    curveTo(18.3254f, 1.9322f, 17.8027f, 2.4548f, 17.8027f, 3.0975f)
                    curveTo(17.8027f, 3.7402f, 18.3254f, 4.2628f, 18.968f, 4.2628f)
                    horizontalLineTo(21.5171f)
                    curveTo(22.4987f, 4.2628f, 23.297f, 3.4645f, 23.297f, 2.483f)
                    curveTo(23.297f, 1.5014f, 22.4987f, 0.7031f, 21.5171f, 0.7031f)
                    curveTo(20.7854f, 0.7031f, 20.1366f, 1.1423f, 19.8648f, 1.8225f)
                    curveTo(19.8301f, 1.9092f, 19.7621f, 1.9786f, 19.6759f, 2.0156f)
                    curveTo(19.5896f, 2.0522f, 19.4926f, 2.0531f, 19.4059f, 2.018f)
                    curveTo(19.2662f, 1.9612f, 19.119f, 1.9327f, 18.968f, 1.9327f)
                    verticalLineTo(1.9322f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(5.6081f, 9.21f)
                    horizontalLineTo(3.5302f)
                    curveTo(2.3784f, 9.21f, 1.4414f, 8.2729f, 1.4414f, 7.1212f)
                    curveTo(1.4414f, 5.9695f, 2.3784f, 5.0325f, 3.5302f, 5.0325f)
                    curveTo(4.2811f, 5.0325f, 4.9575f, 5.4267f, 5.3292f, 6.059f)
                    curveTo(5.4206f, 6.0426f, 5.5139f, 6.0347f, 5.6081f, 6.0347f)
                    curveTo(6.4837f, 6.0347f, 7.1962f, 6.7472f, 7.1962f, 7.6228f)
                    curveTo(7.1962f, 8.4984f, 6.4837f, 9.2109f, 5.6081f, 9.2109f)
                    verticalLineTo(9.21f)
                    close()
                    moveTo(3.5302f, 5.7351f)
                    curveTo(2.7661f, 5.7351f, 2.1445f, 6.3567f, 2.1445f, 7.1207f)
                    curveTo(2.1445f, 7.8848f, 2.7661f, 8.5064f, 3.5302f, 8.5064f)
                    horizontalLineTo(5.6081f)
                    curveTo(6.0961f, 8.5064f, 6.4931f, 8.1094f, 6.4931f, 7.6214f)
                    curveTo(6.4931f, 7.1334f, 6.0961f, 6.7364f, 5.6081f, 6.7364f)
                    curveTo(5.4933f, 6.7364f, 5.3817f, 6.7584f, 5.2758f, 6.8011f)
                    curveTo(5.1891f, 6.8362f, 5.092f, 6.8353f, 5.0058f, 6.7987f)
                    curveTo(4.9195f, 6.7622f, 4.8516f, 6.6928f, 4.8169f, 6.6056f)
                    curveTo(4.605f, 6.0764f, 4.1002f, 5.7342f, 3.5302f, 5.7342f)
                    verticalLineTo(5.7351f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(17.7434f, 21.833f)
                    curveTo(17.7237f, 21.833f, 17.704f, 21.8312f, 17.6843f, 21.8279f)
                    curveTo(17.5151f, 21.7993f, 17.3918f, 21.653f, 17.3918f, 21.4815f)
                    curveTo(17.3918f, 20.6185f, 16.5907f, 19.936f, 16.5828f, 19.929f)
                    curveTo(16.4342f, 19.8043f, 16.4145f, 19.583f, 16.5387f, 19.434f)
                    curveTo(16.6629f, 19.2854f, 16.8837f, 19.2648f, 17.0328f, 19.3885f)
                    curveTo(17.0618f, 19.4129f, 17.5339f, 19.8109f, 17.8385f, 20.4338f)
                    curveTo(18.0101f, 20.1273f, 18.2299f, 19.7977f, 18.4924f, 19.5352f)
                    curveTo(18.6298f, 19.3979f, 18.8524f, 19.3979f, 18.9898f, 19.5352f)
                    curveTo(19.1271f, 19.6726f, 19.1271f, 19.8952f, 18.9898f, 20.0326f)
                    curveTo(18.4324f, 20.5904f, 18.079f, 21.5874f, 18.0757f, 21.5977f)
                    curveTo(18.0256f, 21.7407f, 17.891f, 21.8335f, 17.7439f, 21.8335f)
                    lineTo(17.7434f, 21.833f)
                    close()
                }
            }
        }
        .build()
        return _playground!!
    }

private var _playground: ImageVector? = null
