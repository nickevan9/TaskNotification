package com.example.tasknotification.view.home.tabtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.data.repository.TaskRepository
import com.example.tasknotification.view.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by nickevan on 22,October,2019
 */

class TaskViewModel(private val taskRepository: TaskRepository) : BaseViewModel() {

    val mAllTasks: LiveData<List<Task>> = taskRepository.allTask


    fun loadTaskByDate(dateSt: Date, dateEt: Date): LiveData<List<Task>> {
        showLoading.value = false
        return taskRepository.getTaskByDate(dateSt, dateEt)
    }

    fun loadTaskByDateAndStatus(date: Date, status: Int): LiveData<List<Task>> {
        showLoading.value = false
        return taskRepository.getTaskByDateAndStatus(date, status)
    }

    fun insertTask(task: Task) = viewModelScope.launch(exceptionHandler) {
        statusDB.value = taskRepository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(exceptionHandler) {
        statusDB.value = taskRepository.updateTask(task)
    }
}