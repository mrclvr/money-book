package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import com.lvrmrc.moneybook.presentation.ui.theme.Purple700
import com.lvrmrc.moneybook.presentation.ui.theme.Teal200

sealed class ColorValue(val name: Name, val value: Color) {
    data object Purple : ColorValue(Name.PURPLE, Purple700)
    data object Teal : ColorValue(Name.TEAL, Teal200)

    companion object {
        fun getColorValue(name: Name): Color {
            return ColorValue::class.sealedSubclasses.map { it.objectInstance as ColorValue }.singleOrNull { it.name == name }.let {
                when (it) {
                    null -> Teal200
                    else -> it.value
                }
            }
        }
    }

    enum class Name {
        PURPLE, TEAL
    }
}