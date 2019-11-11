package com.example.tasknotification.view.dialog

import androidx.lifecycle.viewModelScope
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.data.repository.ProjectRepo
import com.example.tasknotification.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by nickevan on 18,October,2019
 */

class DialogProjectViewmodel(
    private val projectRepo: ProjectRepo
) : BaseViewModel() {

    private suspend fun insertProject(project: Project) = projectRepo.insertProject(project)

    fun insertProjectDB(project: Project) {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                insertProject(project)
            }
        }
    }
}