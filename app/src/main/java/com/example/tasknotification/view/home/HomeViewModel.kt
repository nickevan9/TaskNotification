package com.example.tasknotification.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.data.repository.TaskRepo
import com.example.tasknotification.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class HomeViewModel(
    private val taskRepo: TaskRepo
) : BaseViewModel() {

    var listTask = MutableLiveData<MutableList<Task>>()

    private suspend fun loadAllTask() = taskRepo.getAllTask()

    private suspend fun loadTaskByProject(idProject: String) =
        taskRepo.getAllTaskByProject(idProject)

    private suspend fun loadTaskByDate(date: Date) = taskRepo.getTaskByDate(date)

    private suspend fun loadTaskByName(name: String) = taskRepo.getTaskByName(name)

    private suspend fun insertTask(task: Task) = taskRepo.insertTask(task)

    private suspend fun updateTask(task: Task) = taskRepo.updateTask(task)

    private suspend fun deleteTask(task: Task) = taskRepo.deleteTask(task)

    fun loadListAllTask(): MutableLiveData<MutableList<Task>> {
        viewModelScope.launch(exceptionHandler) {
            listTask.value = withContext(Dispatchers.IO) {
                loadAllTask()
            }
        }
        return listTask
    }

    fun loadListByProject(idProject: String): MutableLiveData<MutableList<Task>> {
        viewModelScope.launch(exceptionHandler) {
            listTask.value = withContext(Dispatchers.IO) {
                loadTaskByProject(idProject)
            }
        }
        return listTask
    }

    fun loadListByDate(date: Date): MutableLiveData<MutableList<Task>> {
        showLoading.value = true
        viewModelScope.launch(exceptionHandler) {
            listTask.value = withContext(Dispatchers.IO) {
                loadTaskByDate(date)
            }
            showLoading.value = false
        }
        return listTask
    }

    fun loadListByName(name: String): MutableLiveData<MutableList<Task>> {
        showLoading.value = true
        viewModelScope.launch(exceptionHandler) {
            listTask.value = withContext(Dispatchers.IO) {
                loadTaskByName(name)
            }
            showLoading.value = false

        }
        return listTask
    }

    fun insertTaskDB(task: Task) {
        viewModelScope.launch(exceptionHandler) {
            statusDB.value = withContext(Dispatchers.IO) {
                insertTask(task)
            }.toString()
        }
    }

    fun updateTaskDB(task: Task) {
        viewModelScope.launch(exceptionHandler) {
            statusDB.value = withContext(Dispatchers.IO) {
                updateTask(task)
            }.toString()
        }
    }

    fun deleteTaskDB(task: Task) {
        viewModelScope.launch(exceptionHandler) {
            statusDB.value = withContext(Dispatchers.IO) {
                deleteTask(task)
            }.toString()
        }
    }
}
