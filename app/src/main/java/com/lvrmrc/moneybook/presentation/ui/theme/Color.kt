package com.lvrmrc.moneybook.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import com.lvrmrc.moneybook.domain.model.ColorValue

val primary50 = Color(0xFFefe6f7)
val primary100 = Color(0xFFd6c1ec)
val primary200 = Color(0xFFbb98e1)
val primary = Color(0xFFa16cd5) //main
val primary400 = Color(0xFF8c4acc)
val primary500 = Color(0xFF7726bf)
val primary600 = Color(0xFF6d20b9)
val primary700 = Color(0xFF6016b0)
val primary800 = Color(0xFF540ea8)
val primary900 = Color(0xFF41009a)
val complementary50 = Color(0xFFf2f9ea)
val complementary100 = Color(0xFFdef0cb)
val complementary200 = Color(0xFFc8e7aa)
val complementary300 = Color(0xFFb2dd87)
val complementary = Color(0xFFa1d56c) //main
val complementary500 = Color(0xFF90cd52)
val complementary600 = Color(0xFF81bd4a)
val complementary700 = Color(0xFF6ca83f)
val complementary800 = Color(0xFF599436)
val complementary900 = Color(0xFF377225)
val analogous1_50 = Color(0xFFeaebf9)
val analogous1_100 = Color(0xFFcbcbf1)
val analogous1_200 = Color(0xFFa8aae7)
val analogous1_300 = Color(0xFF8588dd)
val analogous1 = Color(0xFF6c6cd5) //main
val analogous1_500 = Color(0xFF5550cc)
val analogous1_600 = Color(0xFF4f48c1)
val analogous1_700 = Color(0xFF463db5)
val analogous1_800 = Color(0xFF3f32a9)
val analogous1_900 = Color(0xFF341d92)
val analogous2_50 = Color(0xFFf8e6f7)
val analogous2_100 = Color(0xFFedc1ec)
val analogous2_200 = Color(0xFFe298e1)
val analogous2 = Color(0xFFd56cd5) //main
val analogous2_400 = Color(0xFFca48cb)
val analogous2_500 = Color(0xFFbe23c0)
val analogous2_600 = Color(0xFFaf20ba)
val analogous2_700 = Color(0xFF9b1ab3)
val analogous2_800 = Color(0xFF8a16ac)
val analogous2_900 = Color(0xFF6a109f)
val triadic1_50 = Color(0xFFf6e5ee)
val triadic1_100 = Color(0xFFeabdd6)
val triadic1_200 = Color(0xFFdf94bb)
val triadic1 = Color(0xFFd56ca1) //main
val triadic1_400 = Color(0xFFcf518c)
val triadic1_500 = Color(0xFFce3878)
val triadic1_600 = Color(0xFFbd3673)
val triadic1_700 = Color(0xFFa7326b)
val triadic1_800 = Color(0xFF912e63)
val triadic1_900 = Color(0xFF6d2655)
val triadic2_50 = Color(0xFFf7eee4)
val triadic2_100 = Color(0xFFedd5bb)
val triadic2_200 = Color(0xFFe1ba91)
val triadic2 = Color(0xFFd5a16c) //main
val triadic2_400 = Color(0xFFcd8f57)
val triadic2_500 = Color(0xFFc77e42)
val triadic2_600 = Color(0xFFc2753f)
val triadic2_700 = Color(0xFFba693a)
val triadic2_800 = Color(0xFFb25c34)
val triadic2_900 = Color(0xFFa7482b)

val colorsMap = mapOf(
    ColorValue.Name.Primary300 to primary,
    ColorValue.Name.Primary500 to primary500,
    ColorValue.Name.Complementary300 to complementary300,
    ColorValue.Name.Complementary500 to complementary500,
    ColorValue.Name.AnalogousOne500 to analogous1_500,
    ColorValue.Name.AnalogousTwo500 to analogous2_500,
    ColorValue.Name.TriadicOne500 to triadic1_500,
    ColorValue.Name.TriadicTwo500 to triadic2_500,
)

fun Color.lighter(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.White.toArgb(), factor))

fun Color.darker(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.Black.toArgb(), factor))


//fun String.toColor() = Color(android.graphics.Color.parseColor(this))

val slateBlue50 = Color(0xFFeee7fa)
val slateBlue100 = Color(0xFFd2c4f3)
val slateBlue200 = Color(0xFFb59ceb)
val slateBlue300 = Color(0xFF9673e4)
val slateBlue = Color(0xFF7d53de)
val slateBlue500 = Color(0xFF6233d7)
val slateBlue600 = Color(0xFF572ed1)
val slateBlue700 = Color(0xFF4626c8)
val slateBlue800 = Color(0xFF3320c1)
val slateBlue900 = Color(0xFF0713b3)

val yellowGreen50 = Color(0xFFf1f8e6)
val yellowGreen100 = Color(0xFFdcedc0)
val yellowGreen200 = Color(0xFFc4e196)
val yellowGreen300 = Color(0xFFabd56a)
val yellowGreen400 = Color(0xFF97cc46)
val yellowGreen = Color(0xFF84c318)
val yellowGreen600 = Color(0xFF74b30e)
val yellowGreen700 = Color(0xFF5f9f00)
val yellowGreen800 = Color(0xFF498c00)
val yellowGreen900 = Color(0xFF1a6a00)

val orangePeel50 = Color(0xFFfff8e2)
val orangePeel100 = Color(0xFFffecb5)
val orangePeel200 = Color(0xFFffe086)
val orangePeel300 = Color(0xFFffd556)
val orangePeel400 = Color(0xFFffca35)
val orangePeel500 = Color(0xFFffc122)
val orangePeel600 = Color(0xFFffb31e)
val orangePeel = Color(0xFFffa01c)
val orangePeel800 = Color(0xFFfe901b)
val orangePeel900 = Color(0xFFfd7119)

val outerSpace50 = Color(0xFFf9fbfb)
val outerSpace100 = Color(0xFFf5f6f6)
val outerSpace200 = Color(0xFFeff0f0)
val outerSpace300 = Color(0xFFe2e3e3)
val outerSpace400 = Color(0xFFbfc0c0)
val outerSpace500 = Color(0xFFa1a2a2)
val outerSpace600 = Color(0xFF777878)
val outerSpace700 = Color(0xFF636464)
val outerSpace = Color(0xFF444545)
val outerSpace900 = Color(0xFF232424)