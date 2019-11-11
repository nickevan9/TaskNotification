package com.example.tasknotification.data.repository

import com.example.tasknotification.data.local.Task
import java.util.*

/**
 * Created by nickevan on 15,October,2019
 */
interface TaskRepo {
    suspend fun getAllTask(): MutableList<Task>?

    suspend fun getAllTaskByProject(idProject: String): MutableList<Task>?

    suspend fun getTaskByDate(date: Date): MutableList<Task>?

    suspend fun getTaskByName(name: String): MutableList<Task>?

    suspend fun insertTask(task: Task): String

    suspend fun updateTask(task: Task): String

    suspend fun deleteTask(task: Task): String
}