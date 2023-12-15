package com.lvrmrc.moneybook.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

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
