package com.example.tasknotification.di

import android.content.Context
import androidx.room.Room
import com.example.tasknotification.data.database.db.AppDatabase
import org.koin.dsl.module

/**
 * Created by nickevan on 17,October,2019
 */

val databaseModule = module {
    single { createAppDatabase(get(), get()) }
    single { createDatabaseName() }
    single { createTaskDao(get()) }
    single { createProjectDao(get()) }
}

fun createDatabaseName() = "task_db"

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

fun createTaskDao(appDatabase: AppDatabase) = appDatabase.taskDao()

fun createProjectDao(appDatabase: AppDatabase) = appDatabase.projectDao()