package com.example.tasknotification.view.home.tabmenu


import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.data.repository.ProjectRepository
import com.example.tasknotification.view.BaseViewModel
import kotlinx.coroutines.launch


/**
 * Created by nickevan on 17,October,2019
 */

class MenuViewModel(private val projectRepository: ProjectRepository) :
    BaseViewModel() {

    // LiveData gives us updated words when they change.
    val mAllProject: LiveData<List<Project>> = projectRepository.allProjects

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the mainthread, blocking
     * the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called viewModelScope which we
     * can use here.
     */
    fun insert(project: Project) = viewModelScope.launch {
        statusDB.value = projectRepository.insertProject(project)
    }

}