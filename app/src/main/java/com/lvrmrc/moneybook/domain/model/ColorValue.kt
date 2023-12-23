package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import com.lvrmrc.moneybook.presentation.ui.theme.slateBlue
import com.lvrmrc.moneybook.presentation.ui.theme.triadic1_500

sealed class ColorValue(val name: Name, val value: Color) {
    data object Triadic500 : ColorValue(Name.TriadicOne500, triadic1_500)
//    data object OrangePeel : ColorValue(Name.TEAL, orangePeel)

    companion object {
        fun getColorValue(name: Name): Color {
            return ColorValue::class.sealedSubclasses.map { it.objectInstance as ColorValue }.singleOrNull { it.name == name }.let {
                when (it) {
                    null -> slateBlue
                    else -> it.value
                }
            }
        }
    }

    enum class Name {
        Primary300, Primary500, Complementary300, Complementary500, AnalogousOne500, AnalogousTwo500, TriadicOne500, TriadicTwo500,
    }
}


