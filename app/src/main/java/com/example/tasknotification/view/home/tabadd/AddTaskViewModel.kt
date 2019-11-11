package com.example.tasknotification.view.home.tabadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.data.repository.ProjectRepository
import com.example.tasknotification.data.repository.TaskRepository
import com.example.tasknotification.utils.FAILED
import com.example.tasknotification.utils.SUCCESS
import com.example.tasknotification.view.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by nickevan on 23,October,2019
 */

class AddTaskViewModel(
    private val mTaskRepository: TaskRepository,
    private val mProjectRepository: ProjectRepository
) : BaseViewModel() {
    val mAllProject: LiveData<List<Project>> = mProjectRepository.allProjects

    fun insertTask(task: Task) = viewModelScope.launch(exceptionHandler) {
        statusDB.value = mTaskRepository.insertTask(task)
    }

    fun updateProject(project: Project) = viewModelScope.launch(exceptionHandler) {
        statusDB.value = mProjectRepository.updateProject(project)
    }
}