package com.example.tasknotification.data.local

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by nickevan on 15,October,2019
 */
@Entity(
    tableName = "task"
//    foreignKeys = [(ForeignKey(
//        entity = Project::class,
//        childColumns = ["id_project"],
//        parentColumns = ["id_project"],
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    ))]
)

@Parcelize

data class Task(
    @PrimaryKey
    @ColumnInfo(name = "id_task")
    var idTask: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "id_project")
    var idProject: String = "",

    @ColumnInfo(name = "name_project")
    var nameProject: String = "",

    @ColumnInfo(name = "user_id")
    var userId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "date_day")
    var dateDay: Date = Date(),

    @ColumnInfo(name = "start_time")
    var startTime: Date = Date(),

    @ColumnInfo(name = "end_time")
    var endTime: Date = Date(),

    @ColumnInfo(name = "remind_time")
    var remindTime: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "status_check")
    var statusCheck: Int = 0,

    @ColumnInfo(name = "status_task")
    var status: Int = 0

) : Parcelable
