package com.example.tasknotification.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasknotification.data.local.Task
import java.util.*

/**
 * Created by nickevan on 15,October,2019
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY start_time DESC")
    fun getAllTask(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id_task = :idProject ORDER BY start_time DESC")
    fun getAllTaskByProject(idProject: String): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE start_time BETWEEN :daySt AND :dateEt  ORDER BY start_time DESC")
    fun getTaskByDate(daySt: Date, dateEt : Date): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE date_day = :date AND status_task = :status ORDER BY start_time DESC")
    fun getTaskByDateAndStatus(date: Date, status: Int): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE title LIKE :titleText ORDER BY start_time DESC")
    fun getTaskByName(titleText: String): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(vararg task: Task)

    @Delete
    suspend fun deleteTask(vararg task: Task)

}