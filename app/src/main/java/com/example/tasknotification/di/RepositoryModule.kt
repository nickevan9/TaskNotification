package com.example.tasknotification.di

import com.example.tasknotification.data.repository.ProjectRepository
import com.example.tasknotification.data.repository.TaskRepository
import org.koin.dsl.module

/**
 * Created by nickevan on 15,October,2019
 */

val repositoryModule = module {
    factory { TaskRepository(get()) }
    factory { ProjectRepository(get()) }
}


