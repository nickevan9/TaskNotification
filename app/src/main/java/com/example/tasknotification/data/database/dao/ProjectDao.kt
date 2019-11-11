package com.example.tasknotification.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasknotification.data.local.Project

/**
 * Created by nickevan on 17,October,2019
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAllProject(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProject(project: Project)

    @Delete
    suspend fun deleteProject(vararg project: Project)
}