package com.lvrmrc.moneybook.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class DateTimeUtils {
    companion object {
        fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
            val instant = Instant.ofEpochMilli(timestamp)
            val zone = ZoneId.systemDefault()

            return LocalDateTime.ofInstant(instant, zone)
        }

        fun dateToTimestamp(dateTime: LocalDateTime): Long {
            return dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
        }
    }
}

fun LocalDateTime.localFormat(): String? {
    return this.format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.getDefault())
    )
}