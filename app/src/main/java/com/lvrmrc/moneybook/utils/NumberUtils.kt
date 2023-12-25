package com.lvrmrc.moneybook.utils

import java.text.DecimalFormatSymbols

class NumberUtils {

    companion object {
        
        /**
         * Validates string input allowing only digits and decimal separator
         */
        fun cleanDoubleText(input: String): String {
            val symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
            val decimalSeparator = symbols.decimalSeparator
            val stringBuilder = StringBuilder()
            var hasDecimalSep = false

            if (input.matches("\\D".toRegex())) return ""
            if (input.matches("0+".toRegex())) return "0"

            for (char in input) {
                if (char.isDigit()) {
                    stringBuilder.append(char)
                    continue
                }
                if (decimalSeparator == char && !hasDecimalSep && stringBuilder.isNotEmpty()) {
                    stringBuilder.append(char)
                    hasDecimalSep = true
                }
            }

            return stringBuilder.toString()
        }
    }
}

/**
 * Removes decimal digits when zero, retuning an Int
 */
fun Double.removeDecimal(): Number {
    return if (this.rem(1).equals(0.0)) this.toInt() else this
}

/**
 * Converts Double to float percentage relative to given total
 */
fun Double.toFloatPercentage(total: Double): Float {
    return if (this == 0.0) 0f else (100 * (this / total)).toFloat()
}