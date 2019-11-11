package com.example.tasknotification.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by nickevan on 17,October,2019
 */
@Entity(tableName = "project")

data class Project(

    @PrimaryKey
    @ColumnInfo(name = "id_project")
    var idProject: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "color")
    var color: String = "",

    @ColumnInfo(name = "total_task")
    var totalTask: Int = 0,

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "date_day")
    var dateDay: Date = Date()
)