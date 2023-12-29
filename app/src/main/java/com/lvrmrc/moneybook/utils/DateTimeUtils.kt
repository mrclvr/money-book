package com.lvrmrc.moneybook.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

/**
 * Formats LocalDate to "DD Month YYYY" format in current locale
 */
fun LocalDate.localFormat(): String? {
    return this.format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.getDefault())
    )
}

/**
 * Formats LocalDateTime to "DD Month YYYY" format in current locale
 */
fun LocalDateTime.localFormat(): String? {
    return this.format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.getDefault())
    )
}

/**
 * Converts LocalDateTime to milliseconds
 */
fun LocalDateTime.toMillis(): Long {
    return this.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
}

/**
 * Converts milliseconds to LocalDateTime
 */
fun Long.toLocalDateTime(): LocalDateTime {
    val instant = Instant.ofEpochMilli(this)
    val zone = ZoneId.systemDefault()
    return LocalDateTime.ofInstant(instant, zone)
}