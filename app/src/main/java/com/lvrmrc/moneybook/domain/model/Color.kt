package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

enum class ColorName {
    Primary300, Primary500, Complementary300, Complementary500, AnalogousOne500, AnalogousTwo500, TriadicOne500, TriadicTwo500,
}

fun Color.lighter(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.White.toArgb(), factor))
fun Color.darker(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.Black.toArgb(), factor))
