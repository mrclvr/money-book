package com.lvrmrc.moneybook.data.source.db.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.UUID


class Converters {

    /**
     * UUID to string
     */
    @TypeConverter
    fun idToString(id: UUID?): String? = id?.toString()

    /**
     * String to UUID
     */
    @TypeConverter
    fun stringToUUID(id: String?): UUID? = id?.let { UUID.fromString(id) }

    /**
     * timestamp to LocalDateTime
     */
    @TypeConverter
    fun timestampToLocalDateTime(value: String?): LocalDateTime? = value?.let { LocalDateTime.parse(it) }

    /**
     * LocalDateTime to timestamp
     */
    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): String? = date?.toString()
}