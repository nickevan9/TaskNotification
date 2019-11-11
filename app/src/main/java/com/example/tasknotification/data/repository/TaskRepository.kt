package com.example.tasknotification.data.repository

import androidx.lifecycle.LiveData
import com.example.tasknotification.data.database.dao.TaskDao
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.utils.FAILED
import com.example.tasknotification.utils.SUCCESS
import java.lang.Exception
import java.util.*

/**
 * Created by nickevan on 21,October,2019
 */
class TaskRepository(private val taskDao: TaskDao) {
    val allTask: LiveData<List<Task>> = taskDao.getAllTask()

    fun getTaskByProject(idProject: String): LiveData<List<Task>> {
        return taskDao.getAllTaskByProject(idProject)
    }

    fun getTaskByDate(dateSt: Date, dateEt: Date): LiveData<List<Task>> {
        return taskDao.getTaskByDate(dateSt, dateEt)
    }

    fun getTaskByDateAndStatus(date: Date, status: Int): LiveData<List<Task>> {
        return taskDao.getTaskByDateAndStatus(date, status)
    }

    fun getTaskByName(titleText: String): LiveData<List<Task>> {
        return taskDao.getTaskByName(titleText)
    }

    suspend fun insertTask(task: Task): String {
        return try {
            taskDao.insertTask(task)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }

    }

    suspend fun deleteTask(task: Task): String {
        return try {
            taskDao.deleteTask(task)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }
    }

    suspend fun updateTask(task: Task): String {
        return try {
            taskDao.updateTask(task)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }
    }
}