package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import com.lvrmrc.moneybook.data.colorsMap

/**
 * Color names identifiers
 */
enum class ColorName {
    ORANGE_1, ORANGE_2, ORANGE_3, ORANGE_4, RED_1, RED_2, RED_3, RED_4, WATERMELON_1, WATERMELON_2, WATERMELON_3, WATERMELON_4, FUCHSIA_1, FUCHSIA_2, FUCHSIA_3, FUCHSIA_4, PURPLE_1, PURPLE_2, PURPLE_3, PURPLE_4, BLUE_1, BLUE_2, BLUE_3, BLUE_4, AQUA_1, AQUA_2, AQUA_3, AQUA_4, GREEN_1, GREEN_2, GREEN_3, GREEN_4
}

/**
 * Gets color name from color map by its value
 */
fun getColorName(color: Color) = colorsMap.entries.find { it.value == color }?.key ?: ColorName.PURPLE_4

/**
 * Return lighter shade of given color
 */
fun Color.lighten(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.White.toArgb(), factor))

/**
 * Return darker shade of given color
 */
fun Color.darken(factor: Float = 1f) = Color(ColorUtils.blendARGB(this.toArgb(), Color.Black.toArgb(), factor))
