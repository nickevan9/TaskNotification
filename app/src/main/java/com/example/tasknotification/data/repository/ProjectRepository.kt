package com.example.tasknotification.data.repository

import androidx.lifecycle.LiveData
import com.example.tasknotification.data.database.dao.ProjectDao
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.utils.FAILED
import com.example.tasknotification.utils.SUCCESS
import java.lang.Exception

/**
 * Created by nickevan on 19,October,2019
 */

class ProjectRepository(private val projectDao: ProjectDao) {
    val allProjects: LiveData<List<Project>> = projectDao.getAllProject()

    suspend fun insertProject(project: Project): String {
        return try {
            projectDao.insertProject(project)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }

    }

    suspend fun deleteProject(project: Project): String {
        return try {
            projectDao.deleteProject(project)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }

    }

    suspend fun updateProject(project: Project): String {
        return try {
            projectDao.updateProject(project)
            SUCCESS
        } catch (e: Exception) {
            FAILED
        }

    }
}