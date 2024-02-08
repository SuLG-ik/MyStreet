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

public val UIKitIconPack.Layers: ImageVector
    get() {
        if (_layers != null) {
            return _layers!!
        }
        _layers = Builder(name = "Layers", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
            }
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(23.9965f, 12.0017f)
                    curveTo(23.9869f, 11.9334f, 23.9574f, 11.8693f, 23.9117f, 11.8176f)
                    curveTo(23.866f, 11.7659f, 23.8061f, 11.7288f, 23.7394f, 11.7109f)
                    curveTo(23.7166f, 11.7049f, 21.6245f, 11.1395f, 18.8957f, 10.0574f)
                    curveTo(21.8231f, 8.753f, 23.7966f, 7.5777f, 23.8292f, 7.5582f)
                    curveTo(23.8884f, 7.5226f, 23.9358f, 7.4705f, 23.9655f, 7.4082f)
                    curveTo(23.9952f, 7.3459f, 24.006f, 7.2763f, 23.9964f, 7.2079f)
                    curveTo(23.9868f, 7.1396f, 23.9574f, 7.0756f, 23.9117f, 7.0239f)
                    curveTo(23.866f, 6.9721f, 23.8061f, 6.935f, 23.7394f, 6.9171f)
                    curveTo(23.6756f, 6.9f, 17.3022f, 5.1683f, 12.188f, 1.9296f)
                    curveTo(12.1317f, 1.894f, 12.0665f, 1.875f, 11.9999f, 1.875f)
                    curveTo(11.9333f, 1.875f, 11.8681f, 1.894f, 11.8118f, 1.9296f)
                    curveTo(9.7402f, 3.2415f, 7.2407f, 4.4575f, 4.3827f, 5.5437f)
                    curveTo(4.2956f, 5.5768f, 4.2252f, 5.6432f, 4.1869f, 5.7283f)
                    curveTo(4.1487f, 5.8134f, 4.1459f, 5.9101f, 4.179f, 5.9973f)
                    curveTo(4.2122f, 6.0844f, 4.2786f, 6.1549f, 4.3636f, 6.1931f)
                    curveTo(4.4487f, 6.2313f, 4.5454f, 6.2341f, 4.6326f, 6.201f)
                    curveTo(7.4488f, 5.1306f, 9.9262f, 3.9336f, 11.9999f, 2.6418f)
                    curveTo(16.0834f, 5.1843f, 20.8817f, 6.7893f, 22.7533f, 7.3611f)
                    curveTo(21.1047f, 8.2718f, 16.8062f, 10.5003f, 11.9999f, 11.8214f)
                    curveTo(7.1951f, 10.5007f, 2.8979f, 8.2732f, 1.2482f, 7.362f)
                    curveTo(1.7022f, 7.2239f, 2.3294f, 7.0248f, 3.0824f, 6.7639f)
                    curveTo(3.126f, 6.7488f, 3.1662f, 6.7252f, 3.2008f, 6.6946f)
                    curveTo(3.2353f, 6.6639f, 3.2634f, 6.6268f, 3.2836f, 6.5852f)
                    curveTo(3.3037f, 6.5437f, 3.3155f, 6.4986f, 3.3182f, 6.4525f)
                    curveTo(3.321f, 6.4064f, 3.3146f, 6.3603f, 3.2995f, 6.3166f)
                    curveTo(3.2844f, 6.273f, 3.2608f, 6.2328f, 3.2302f, 6.1983f)
                    curveTo(3.1995f, 6.1637f, 3.1624f, 6.1356f, 3.1208f, 6.1154f)
                    curveTo(3.0793f, 6.0953f, 3.0342f, 6.0835f, 2.9881f, 6.0807f)
                    curveTo(2.942f, 6.078f, 2.8958f, 6.0844f, 2.8522f, 6.0995f)
                    curveTo(1.3053f, 6.6353f, 0.2707f, 6.9144f, 0.2604f, 6.9171f)
                    curveTo(0.1937f, 6.935f, 0.1338f, 6.9721f, 0.0881f, 7.0239f)
                    curveTo(0.0424f, 7.0756f, 0.013f, 7.1396f, 0.0034f, 7.2079f)
                    curveTo(-0.0062f, 7.2763f, 0.0046f, 7.3459f, 0.0343f, 7.4082f)
                    curveTo(0.064f, 7.4705f, 0.1114f, 7.5226f, 0.1706f, 7.5582f)
                    curveTo(0.2031f, 7.5777f, 2.1767f, 8.753f, 5.1041f, 10.0574f)
                    curveTo(2.3774f, 11.1385f, 0.2831f, 11.7049f, 0.2604f, 11.7109f)
                    curveTo(0.1937f, 11.7288f, 0.1338f, 11.766f, 0.0881f, 11.8177f)
                    curveTo(0.0424f, 11.8694f, 0.013f, 11.9334f, 0.0034f, 12.0017f)
                    curveTo(-0.0062f, 12.0701f, 0.0046f, 12.1397f, 0.0343f, 12.202f)
                    curveTo(0.064f, 12.2643f, 0.1114f, 12.3164f, 0.1706f, 12.352f)
                    curveTo(0.2031f, 12.3715f, 2.1767f, 13.5469f, 5.1041f, 14.8512f)
                    curveTo(2.3775f, 15.9323f, 0.2831f, 16.4987f, 0.2604f, 16.5047f)
                    curveTo(0.1937f, 16.5226f, 0.1338f, 16.5598f, 0.0881f, 16.6115f)
                    curveTo(0.0424f, 16.6632f, 0.013f, 16.7272f, 0.0034f, 16.7956f)
                    curveTo(-0.0062f, 16.8639f, 0.0046f, 16.9336f, 0.0343f, 16.9958f)
                    curveTo(0.064f, 17.0581f, 0.1114f, 17.1103f, 0.1706f, 17.1458f)
                    curveTo(0.2138f, 17.1717f, 1.2476f, 17.7899f, 2.9484f, 18.634f)
                    curveTo(2.9897f, 18.6545f, 3.0347f, 18.6667f, 3.0808f, 18.6698f)
                    curveTo(3.1268f, 18.673f, 3.173f, 18.667f, 3.2168f, 18.6523f)
                    curveTo(3.2605f, 18.6375f, 3.301f, 18.6143f, 3.3358f, 18.584f)
                    curveTo(3.3705f, 18.5536f, 3.399f, 18.5167f, 3.4195f, 18.4754f)
                    curveTo(3.44f, 18.434f, 3.4522f, 18.389f, 3.4554f, 18.3429f)
                    curveTo(3.4585f, 18.2969f, 3.4526f, 18.2507f, 3.4378f, 18.2069f)
                    curveTo(3.4231f, 18.1631f, 3.3999f, 18.1227f, 3.3695f, 18.0879f)
                    curveTo(3.3392f, 18.0531f, 3.3023f, 18.0247f, 3.2609f, 18.0042f)
                    curveTo(2.4082f, 17.5809f, 1.7187f, 17.2112f, 1.2461f, 16.9495f)
                    curveTo(2.2189f, 16.6533f, 3.981f, 16.0792f, 6.0078f, 15.2443f)
                    curveTo(7.7612f, 15.9889f, 9.7869f, 16.7419f, 11.9076f, 17.3189f)
                    curveTo(11.9681f, 17.3353f, 12.0318f, 17.3353f, 12.0922f, 17.3189f)
                    curveTo(12.5017f, 17.2073f, 12.9094f, 17.0892f, 13.3152f, 16.9646f)
                    curveTo(13.4043f, 16.9373f, 13.4789f, 16.8756f, 13.5226f, 16.7932f)
                    curveTo(13.5663f, 16.7109f, 13.5755f, 16.6145f, 13.5481f, 16.5254f)
                    curveTo(13.5208f, 16.4362f, 13.4591f, 16.3616f, 13.3768f, 16.3179f)
                    curveTo(13.2944f, 16.2742f, 13.198f, 16.2651f, 13.1089f, 16.2924f)
                    curveTo(12.7408f, 16.4055f, 12.3711f, 16.5131f, 11.9999f, 16.6152f)
                    curveTo(7.1944f, 15.2943f, 2.8964f, 13.0663f, 1.2472f, 12.1553f)
                    curveTo(2.2202f, 11.859f, 3.9818f, 11.2851f, 6.0078f, 10.4505f)
                    curveTo(7.7612f, 11.1951f, 9.7869f, 11.9481f, 11.9076f, 12.5251f)
                    curveTo(11.9681f, 12.5415f, 12.0318f, 12.5415f, 12.0922f, 12.5251f)
                    curveTo(14.2129f, 11.9481f, 16.2386f, 11.1951f, 17.9921f, 10.4505f)
                    curveTo(20.0183f, 11.2851f, 21.78f, 11.8592f, 22.753f, 12.1555f)
                    curveTo(21.4132f, 12.8962f, 18.3243f, 14.5075f, 14.6408f, 15.7902f)
                    curveTo(14.5546f, 15.8223f, 14.4844f, 15.8869f, 14.4452f, 15.9701f)
                    curveTo(14.4061f, 16.0533f, 14.4011f, 16.1486f, 14.4313f, 16.2355f)
                    curveTo(14.4616f, 16.3223f, 14.5247f, 16.3939f, 14.607f, 16.4348f)
                    curveTo(14.6894f, 16.4757f, 14.7846f, 16.4827f, 14.8721f, 16.4542f)
                    curveTo(15.9751f, 16.0701f, 17.026f, 15.6567f, 17.9944f, 15.2453f)
                    curveTo(20.0198f, 16.0795f, 21.7807f, 16.6532f, 22.7533f, 16.9494f)
                    curveTo(21.8477f, 17.4505f, 20.1438f, 18.3492f, 18.0061f, 19.2706f)
                    curveTo(17.9205f, 19.3075f, 17.853f, 19.3769f, 17.8186f, 19.4635f)
                    curveTo(17.7841f, 19.5502f, 17.7855f, 19.647f, 17.8224f, 19.7326f)
                    curveTo(17.8496f, 19.7957f, 17.8947f, 19.8494f, 17.9521f, 19.8872f)
                    curveTo(18.0095f, 19.925f, 18.0768f, 19.9451f, 18.1455f, 19.9451f)
                    curveTo(18.1919f, 19.9451f, 18.2391f, 19.9358f, 18.2845f, 19.9162f)
                    curveTo(21.5386f, 18.5136f, 23.8067f, 17.1593f, 23.8292f, 17.1458f)
                    curveTo(23.8884f, 17.1103f, 23.9358f, 17.0581f, 23.9655f, 16.9958f)
                    curveTo(23.9952f, 16.9336f, 24.006f, 16.8639f, 23.9964f, 16.7956f)
                    curveTo(23.9868f, 16.7272f, 23.9574f, 16.6632f, 23.9117f, 16.6115f)
                    curveTo(23.866f, 16.5598f, 23.8061f, 16.5226f, 23.7394f, 16.5047f)
                    curveTo(23.7166f, 16.4987f, 21.6235f, 15.9326f, 18.8979f, 14.8521f)
                    curveTo(21.8239f, 13.5477f, 23.8015f, 12.3686f, 23.8292f, 12.352f)
                    curveTo(23.8884f, 12.3165f, 23.9358f, 12.2643f, 23.9656f, 12.202f)
                    curveTo(23.9953f, 12.1397f, 24.0061f, 12.0701f, 23.9965f, 12.0017f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(16.5816f, 19.8605f)
                    curveTo(15.0088f, 20.4848f, 13.4681f, 21.0056f, 12.0004f, 21.409f)
                    curveTo(9.6441f, 20.7615f, 7.153f, 19.8315f, 4.5943f, 18.644f)
                    curveTo(4.5097f, 18.6047f, 4.413f, 18.6007f, 4.3255f, 18.6327f)
                    curveTo(4.2379f, 18.6648f, 4.1667f, 18.7303f, 4.1274f, 18.8149f)
                    curveTo(4.108f, 18.8568f, 4.097f, 18.9021f, 4.095f, 18.9482f)
                    curveTo(4.0931f, 18.9943f, 4.1003f, 19.0404f, 4.1161f, 19.0837f)
                    curveTo(4.132f, 19.1271f, 4.1563f, 19.1669f, 4.1875f, 19.2009f)
                    curveTo(4.2188f, 19.2348f, 4.2564f, 19.2623f, 4.2983f, 19.2818f)
                    curveTo(6.9263f, 20.5014f, 9.4866f, 21.4539f, 11.9081f, 22.1127f)
                    curveTo(11.9685f, 22.1291f, 12.0322f, 22.1291f, 12.0927f, 22.1127f)
                    curveTo(13.6133f, 21.699f, 15.2109f, 21.1611f, 16.841f, 20.5141f)
                    curveTo(16.8839f, 20.497f, 16.9231f, 20.4717f, 16.9562f, 20.4396f)
                    curveTo(16.9893f, 20.4074f, 17.0158f, 20.369f, 17.0341f, 20.3267f)
                    curveTo(17.0524f, 20.2843f, 17.0622f, 20.2387f, 17.0629f, 20.1925f)
                    curveTo(17.0635f, 20.1464f, 17.0551f, 20.1005f, 17.0381f, 20.0576f)
                    curveTo(17.0036f, 19.971f, 16.9362f, 19.9015f, 16.8506f, 19.8646f)
                    curveTo(16.7651f, 19.8276f, 16.6683f, 19.8262f, 16.5816f, 19.8605f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(12.0f, 4.4815f)
                    curveTo(12.0932f, 4.4815f, 12.1827f, 4.4444f, 12.2486f, 4.3785f)
                    curveTo(12.3145f, 4.3125f, 12.3516f, 4.2231f, 12.3516f, 4.1299f)
                    curveTo(12.3516f, 4.0366f, 12.3145f, 3.9472f, 12.2486f, 3.8813f)
                    curveTo(12.1827f, 3.8154f, 12.0932f, 3.7783f, 12.0f, 3.7783f)
                    curveTo(11.9068f, 3.7783f, 11.8173f, 3.8154f, 11.7514f, 3.8813f)
                    curveTo(11.6855f, 3.9472f, 11.6484f, 4.0366f, 11.6484f, 4.1299f)
                    curveTo(11.6484f, 4.2231f, 11.6855f, 4.3125f, 11.7514f, 4.3785f)
                    curveTo(11.8173f, 4.4444f, 11.9068f, 4.4815f, 12.0f, 4.4815f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(12.0f, 6.5947f)
                    curveTo(12.0932f, 6.5947f, 12.1827f, 6.5577f, 12.2486f, 6.4918f)
                    curveTo(12.3145f, 6.4258f, 12.3516f, 6.3364f, 12.3516f, 6.2432f)
                    curveTo(12.3516f, 6.1499f, 12.3145f, 6.0605f, 12.2486f, 5.9946f)
                    curveTo(12.1827f, 5.9286f, 12.0932f, 5.8916f, 12.0f, 5.8916f)
                    curveTo(11.9068f, 5.8916f, 11.8173f, 5.9286f, 11.7514f, 5.9946f)
                    curveTo(11.6855f, 6.0605f, 11.6484f, 6.1499f, 11.6484f, 6.2432f)
                    curveTo(11.6484f, 6.3364f, 11.6855f, 6.4258f, 11.7514f, 6.4918f)
                    curveTo(11.8173f, 6.5577f, 11.9068f, 6.5947f, 12.0f, 6.5947f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(12.0f, 8.708f)
                    curveTo(12.0932f, 8.708f, 12.1827f, 8.671f, 12.2486f, 8.605f)
                    curveTo(12.3145f, 8.5391f, 12.3516f, 8.4497f, 12.3516f, 8.3565f)
                    curveTo(12.3516f, 8.2632f, 12.3145f, 8.1738f, 12.2486f, 8.1079f)
                    curveTo(12.1827f, 8.0419f, 12.0932f, 8.0049f, 12.0f, 8.0049f)
                    curveTo(11.9068f, 8.0049f, 11.8173f, 8.0419f, 11.7514f, 8.1079f)
                    curveTo(11.6855f, 8.1738f, 11.6484f, 8.2632f, 11.6484f, 8.3565f)
                    curveTo(11.6484f, 8.4497f, 11.6855f, 8.5391f, 11.7514f, 8.605f)
                    curveTo(11.8173f, 8.671f, 11.9068f, 8.708f, 12.0f, 8.708f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(12.0f, 10.8218f)
                    curveTo(12.0932f, 10.8218f, 12.1827f, 10.7847f, 12.2486f, 10.7188f)
                    curveTo(12.3145f, 10.6529f, 12.3516f, 10.5635f, 12.3516f, 10.4702f)
                    curveTo(12.3516f, 10.377f, 12.3145f, 10.2876f, 12.2486f, 10.2216f)
                    curveTo(12.1827f, 10.1557f, 12.0932f, 10.1187f, 12.0f, 10.1187f)
                    curveTo(11.9068f, 10.1187f, 11.8173f, 10.1557f, 11.7514f, 10.2216f)
                    curveTo(11.6855f, 10.2876f, 11.6484f, 10.377f, 11.6484f, 10.4702f)
                    curveTo(11.6484f, 10.5635f, 11.6855f, 10.6529f, 11.7514f, 10.7188f)
                    curveTo(11.8173f, 10.7847f, 11.9068f, 10.8218f, 12.0f, 10.8218f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(14.6406f, 9.9316f)
                    curveTo(14.7339f, 9.9316f, 14.8233f, 9.8946f, 14.8892f, 9.8287f)
                    curveTo(14.9551f, 9.7627f, 14.9922f, 9.6733f, 14.9922f, 9.5801f)
                    curveTo(14.9922f, 9.4868f, 14.9551f, 9.3974f, 14.8892f, 9.3315f)
                    curveTo(14.8233f, 9.2656f, 14.7339f, 9.2285f, 14.6406f, 9.2285f)
                    curveTo(14.5474f, 9.2285f, 14.458f, 9.2656f, 14.392f, 9.3315f)
                    curveTo(14.3261f, 9.3974f, 14.2891f, 9.4868f, 14.2891f, 9.5801f)
                    curveTo(14.2891f, 9.6733f, 14.3261f, 9.7627f, 14.392f, 9.8287f)
                    curveTo(14.458f, 9.8946f, 14.5474f, 9.9316f, 14.6406f, 9.9316f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(14.6406f, 7.8301f)
                    curveTo(14.7339f, 7.8301f, 14.8233f, 7.793f, 14.8892f, 7.7271f)
                    curveTo(14.9551f, 7.6612f, 14.9922f, 7.5718f, 14.9922f, 7.4785f)
                    curveTo(14.9922f, 7.3853f, 14.9551f, 7.2958f, 14.8892f, 7.2299f)
                    curveTo(14.8233f, 7.164f, 14.7339f, 7.1269f, 14.6406f, 7.1269f)
                    curveTo(14.5474f, 7.1269f, 14.458f, 7.164f, 14.392f, 7.2299f)
                    curveTo(14.3261f, 7.2958f, 14.2891f, 7.3853f, 14.2891f, 7.4785f)
                    curveTo(14.2891f, 7.5718f, 14.3261f, 7.6612f, 14.392f, 7.7271f)
                    curveTo(14.458f, 7.793f, 14.5474f, 7.8301f, 14.6406f, 7.8301f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(14.6406f, 5.9275f)
                    curveTo(14.7339f, 5.9275f, 14.8233f, 5.8905f, 14.8892f, 5.8245f)
                    curveTo(14.9551f, 5.7586f, 14.9922f, 5.6692f, 14.9922f, 5.5759f)
                    curveTo(14.9922f, 5.4827f, 14.9551f, 5.3933f, 14.8892f, 5.3273f)
                    curveTo(14.8233f, 5.2614f, 14.7339f, 5.2244f, 14.6406f, 5.2244f)
                    curveTo(14.5474f, 5.2244f, 14.458f, 5.2614f, 14.392f, 5.3273f)
                    curveTo(14.3261f, 5.3933f, 14.2891f, 5.4827f, 14.2891f, 5.5759f)
                    curveTo(14.2891f, 5.6692f, 14.3261f, 5.7586f, 14.392f, 5.8245f)
                    curveTo(14.458f, 5.8905f, 14.5474f, 5.9275f, 14.6406f, 5.9275f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(17.1436f, 6.8731f)
                    curveTo(17.2368f, 6.8731f, 17.3262f, 6.836f, 17.3921f, 6.7701f)
                    curveTo(17.4581f, 6.7042f, 17.4951f, 6.6147f, 17.4951f, 6.5215f)
                    curveTo(17.4951f, 6.4282f, 17.4581f, 6.3388f, 17.3921f, 6.2729f)
                    curveTo(17.3262f, 6.207f, 17.2368f, 6.1699f, 17.1436f, 6.1699f)
                    curveTo(17.0503f, 6.1699f, 16.9609f, 6.207f, 16.895f, 6.2729f)
                    curveTo(16.829f, 6.3388f, 16.792f, 6.4282f, 16.792f, 6.5215f)
                    curveTo(16.792f, 6.6147f, 16.829f, 6.7042f, 16.895f, 6.7701f)
                    curveTo(16.9609f, 6.836f, 17.0503f, 6.8731f, 17.1436f, 6.8731f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(17.1436f, 8.9861f)
                    curveTo(17.2368f, 8.9861f, 17.3262f, 8.949f, 17.3921f, 8.8831f)
                    curveTo(17.4581f, 8.8172f, 17.4951f, 8.7278f, 17.4951f, 8.6345f)
                    curveTo(17.4951f, 8.5413f, 17.4581f, 8.4519f, 17.3921f, 8.3859f)
                    curveTo(17.3262f, 8.32f, 17.2368f, 8.283f, 17.1436f, 8.283f)
                    curveTo(17.0503f, 8.283f, 16.9609f, 8.32f, 16.895f, 8.3859f)
                    curveTo(16.829f, 8.4519f, 16.792f, 8.5413f, 16.792f, 8.6345f)
                    curveTo(16.792f, 8.7278f, 16.829f, 8.8172f, 16.895f, 8.8831f)
                    curveTo(16.9609f, 8.949f, 17.0503f, 8.9861f, 17.1436f, 8.9861f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(20.0537f, 7.5781f)
                    curveTo(20.0537f, 7.4849f, 20.0167f, 7.3955f, 19.9507f, 7.3295f)
                    curveTo(19.8848f, 7.2636f, 19.7954f, 7.2266f, 19.7021f, 7.2266f)
                    curveTo(19.6089f, 7.2266f, 19.5195f, 7.2636f, 19.4536f, 7.3295f)
                    curveTo(19.3876f, 7.3955f, 19.3506f, 7.4849f, 19.3506f, 7.5781f)
                    curveTo(19.3506f, 7.6714f, 19.3876f, 7.7608f, 19.4536f, 7.8267f)
                    curveTo(19.5195f, 7.8927f, 19.6089f, 7.9297f, 19.7021f, 7.9297f)
                    curveTo(19.7954f, 7.9297f, 19.8848f, 7.8927f, 19.9507f, 7.8267f)
                    curveTo(20.0167f, 7.7608f, 20.0537f, 7.6714f, 20.0537f, 7.5781f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(9.3594f, 9.9316f)
                    curveTo(9.4526f, 9.9316f, 9.542f, 9.8946f, 9.608f, 9.8287f)
                    curveTo(9.6739f, 9.7627f, 9.7109f, 9.6733f, 9.7109f, 9.5801f)
                    curveTo(9.7109f, 9.4868f, 9.6739f, 9.3974f, 9.608f, 9.3315f)
                    curveTo(9.542f, 9.2656f, 9.4526f, 9.2285f, 9.3594f, 9.2285f)
                    curveTo(9.2661f, 9.2285f, 9.1767f, 9.2656f, 9.1108f, 9.3315f)
                    curveTo(9.0449f, 9.3974f, 9.0078f, 9.4868f, 9.0078f, 9.5801f)
                    curveTo(9.0078f, 9.6733f, 9.0449f, 9.7627f, 9.1108f, 9.8287f)
                    curveTo(9.1767f, 9.8946f, 9.2661f, 9.9316f, 9.3594f, 9.9316f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(9.3594f, 7.8301f)
                    curveTo(9.4526f, 7.8301f, 9.542f, 7.793f, 9.608f, 7.7271f)
                    curveTo(9.6739f, 7.6612f, 9.7109f, 7.5718f, 9.7109f, 7.4785f)
                    curveTo(9.7109f, 7.3853f, 9.6739f, 7.2958f, 9.608f, 7.2299f)
                    curveTo(9.542f, 7.164f, 9.4526f, 7.1269f, 9.3594f, 7.1269f)
                    curveTo(9.2661f, 7.1269f, 9.1767f, 7.164f, 9.1108f, 7.2299f)
                    curveTo(9.0449f, 7.2958f, 9.0078f, 7.3853f, 9.0078f, 7.4785f)
                    curveTo(9.0078f, 7.5718f, 9.0449f, 7.6612f, 9.1108f, 7.7271f)
                    curveTo(9.1767f, 7.793f, 9.2661f, 7.8301f, 9.3594f, 7.8301f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(9.3594f, 5.9275f)
                    curveTo(9.4526f, 5.9275f, 9.542f, 5.8905f, 9.608f, 5.8245f)
                    curveTo(9.6739f, 5.7586f, 9.7109f, 5.6692f, 9.7109f, 5.5759f)
                    curveTo(9.7109f, 5.4827f, 9.6739f, 5.3933f, 9.608f, 5.3273f)
                    curveTo(9.542f, 5.2614f, 9.4526f, 5.2244f, 9.3594f, 5.2244f)
                    curveTo(9.2661f, 5.2244f, 9.1767f, 5.2614f, 9.1108f, 5.3273f)
                    curveTo(9.0449f, 5.3933f, 9.0078f, 5.4827f, 9.0078f, 5.5759f)
                    curveTo(9.0078f, 5.6692f, 9.0449f, 5.7586f, 9.1108f, 5.8245f)
                    curveTo(9.1767f, 5.8905f, 9.2661f, 5.9275f, 9.3594f, 5.9275f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(6.8565f, 6.8731f)
                    curveTo(6.9497f, 6.8731f, 7.0391f, 6.836f, 7.105f, 6.7701f)
                    curveTo(7.171f, 6.7042f, 7.208f, 6.6147f, 7.208f, 6.5215f)
                    curveTo(7.208f, 6.452f, 7.1874f, 6.384f, 7.1488f, 6.3262f)
                    curveTo(7.1101f, 6.2684f, 7.0552f, 6.2233f, 6.991f, 6.1967f)
                    curveTo(6.9267f, 6.1701f, 6.8561f, 6.1631f, 6.7879f, 6.1767f)
                    curveTo(6.7197f, 6.1902f, 6.657f, 6.2237f, 6.6079f, 6.2729f)
                    curveTo(6.5587f, 6.3221f, 6.5252f, 6.3847f, 6.5116f, 6.4529f)
                    curveTo(6.4981f, 6.5211f, 6.505f, 6.5918f, 6.5316f, 6.656f)
                    curveTo(6.5582f, 6.7203f, 6.6033f, 6.7752f, 6.6611f, 6.8138f)
                    curveTo(6.7189f, 6.8524f, 6.7869f, 6.8731f, 6.8565f, 6.8731f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(6.8565f, 8.9861f)
                    curveTo(6.9497f, 8.9861f, 7.0391f, 8.949f, 7.105f, 8.8831f)
                    curveTo(7.171f, 8.8172f, 7.208f, 8.7278f, 7.208f, 8.6345f)
                    curveTo(7.208f, 8.565f, 7.1874f, 8.497f, 7.1488f, 8.4392f)
                    curveTo(7.1101f, 8.3814f, 7.0552f, 8.3363f, 6.991f, 8.3097f)
                    curveTo(6.9267f, 8.2831f, 6.8561f, 8.2761f, 6.7879f, 8.2897f)
                    curveTo(6.7197f, 8.3033f, 6.657f, 8.3368f, 6.6079f, 8.3859f)
                    curveTo(6.5587f, 8.4351f, 6.5252f, 8.4977f, 6.5116f, 8.5659f)
                    curveTo(6.4981f, 8.6341f, 6.505f, 8.7048f, 6.5316f, 8.7691f)
                    curveTo(6.5582f, 8.8333f, 6.6033f, 8.8882f, 6.6611f, 8.9268f)
                    curveTo(6.7189f, 8.9655f, 6.7869f, 8.9861f, 6.8565f, 8.9861f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(4.2979f, 7.9297f)
                    curveTo(4.3911f, 7.9297f, 4.4805f, 7.8927f, 4.5464f, 7.8267f)
                    curveTo(4.6124f, 7.7608f, 4.6494f, 7.6714f, 4.6494f, 7.5781f)
                    curveTo(4.6494f, 7.5086f, 4.6288f, 7.4406f, 4.5902f, 7.3828f)
                    curveTo(4.5515f, 7.325f, 4.4966f, 7.2799f, 4.4324f, 7.2533f)
                    curveTo(4.3682f, 7.2267f, 4.2975f, 7.2197f, 4.2293f, 7.2333f)
                    curveTo(4.1611f, 7.2469f, 4.0984f, 7.2804f, 4.0493f, 7.3295f)
                    curveTo(4.0001f, 7.3787f, 3.9666f, 7.4413f, 3.953f, 7.5095f)
                    curveTo(3.9395f, 7.5777f, 3.9464f, 7.6484f, 3.9731f, 7.7127f)
                    curveTo(3.9997f, 7.7769f, 4.0447f, 7.8318f, 4.1025f, 7.8704f)
                    curveTo(4.1603f, 7.9091f, 4.2283f, 7.9297f, 4.2979f, 7.9297f)
                    close()
                }
            }
        }
        .build()
        return _layers!!
    }

private var _layers: ImageVector? = null
