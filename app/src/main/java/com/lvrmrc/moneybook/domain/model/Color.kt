package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import com.lvrmrc.moneybook.data.colorsMap

/**
 * Color names identifiers
 */
enum class ColorName {
    Primary300, Primary500, Complementary300, Complementary500, AnalogousOne500, AnalogousTwo500, TriadicOne500, TriadicTwo500,
}

/**
 * Gets color name from color map by its value
 */
fun getColorName(color: Color) = colorsMap.entries.find { it.value == color }?.key ?: ColorName.Primary500

/**
 * Return lighter shade of given color
 */
fun Color.lighten(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.White.toArgb(), factor))

/**
 * Return darker shade of given color
 */
fun Color.darken(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.Black.toArgb(), factor))
