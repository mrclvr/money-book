package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import com.lvrmrc.moneybook.presentation.ui.theme.orangePeel
import com.lvrmrc.moneybook.presentation.ui.theme.slateBlue
import com.lvrmrc.moneybook.presentation.ui.theme.triadic1_500

sealed class ColorValue(val name: Name, val value: Color) {
    data object purple200 : ColorValue(Name.PURPLE, triadic1_500)
    data object purple500 : ColorValue(Name.TEAL, orangePeel)

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
        PURPLE, TEAL
    }
}