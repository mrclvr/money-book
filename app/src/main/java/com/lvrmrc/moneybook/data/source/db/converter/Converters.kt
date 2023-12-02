package com.lvrmrc.moneybook.data.source.db.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.UUID


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

    //    @TypeConverter
//    fun saveDate(localDateTime: LocalDateTime?): Long? = localDateTime?.toEpochMilli()
//
//    @TypeConverter
//    fun parseDate(timestampMillis: Long?): LocalDateTime? = timestampMillis?.epochMilliToDateTime()
    @TypeConverter
    fun saveUUID(id: UUID?) = id?.toString()

    @TypeConverter
    fun parseUUID(id: String?) = id?.let { UUID.fromString(id) }

    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}