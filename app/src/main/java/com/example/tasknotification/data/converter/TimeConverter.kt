package com.example.tasknotification.data.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by nickevan on 15,October,2019
 */
class TimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}