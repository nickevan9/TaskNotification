package com.example.tasknotification.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tasknotification.data.converter.TimeConverter
import com.example.tasknotification.data.database.dao.ProjectDao
import com.example.tasknotification.data.database.dao.TaskDao
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.data.local.Task

/**
 * Created by nickevan on 15,October,2019
 */

@Database(entities = [Task::class, Project::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao


}