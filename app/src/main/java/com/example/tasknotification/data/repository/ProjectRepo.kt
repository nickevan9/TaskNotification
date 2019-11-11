package com.example.tasknotification.data.repository

import com.example.tasknotification.data.local.Project

/**
 * Created by nickevan on 17,October,2019
 */
interface ProjectRepo {
    suspend fun getAllProject(): MutableList<Project>?

    suspend fun insertProject(project: Project): String

    suspend fun updateProject(project: Project): String

    suspend fun deleteProject(project: Project): String
}