package com.lvrmrc.moneybook.utils

import androidx.room.TypeConverter
import java.time.LocalDate


class Converters {
//    @TypeConverter
//    fun fromTimestamp(ts: Long?): LocalDate? {
//        return ts?.let { LocalDate.ofEpochDay(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: LocalDate?): Long? {
//        return date?.toEpochDay()
//    }

    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }
}