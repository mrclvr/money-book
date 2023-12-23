package com.lvrmrc.moneybook.utils

import java.text.DecimalFormatSymbols

class NumberUtils {

    companion object {
        fun cleanDoubleText(input: String): String {
            val symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
//            val decimalSeparator = symbols.decimalSeparator
//            val thousandsSeparator = symbols.groupingSeparator
            val decimalSeparators = listOf(
                symbols.monetaryDecimalSeparator
//                , symbols.groupingSeparator
            )

            if (input.matches("\\D".toRegex())) return ""
            if (input.matches("0+".toRegex())) return "0"

            val stringBuilder = StringBuilder()

            var hasDecimalSep = false

            for (char in input) {
                if (char.isDigit()) {
                    stringBuilder.append(char)
                    continue
                }
                if (decimalSeparators.contains(char) && !hasDecimalSep && stringBuilder.isNotEmpty()) {
                    stringBuilder.append(char)
                    hasDecimalSep = true
                }
            }

            return stringBuilder.toString()
        }

        fun getFloatPercentage(value: Double, total: Double): Float {
            return if (value == 0.0) 0f else (100 * (value / total)).toFloat()
        }

        fun removeDecimal(value: Double): Number {
            return if (value.rem(1).equals(0.0)) value.toInt() else value
        }
    }
}


fun Double.removeDecimal(): Number {
    return if (this.rem(1).equals(0.0)) this.toInt() else this
}
