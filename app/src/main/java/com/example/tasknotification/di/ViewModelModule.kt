package com.example.tasknotification.di

import com.example.tasknotification.view.auth.AuthViewModel
import com.example.tasknotification.view.dialog.DialogProjectViewmodel
import com.example.tasknotification.view.home.HomeViewModel
import com.example.tasknotification.view.home.tabadd.AddTaskViewModel
import com.example.tasknotification.view.home.tabmenu.MenuViewModel
import com.example.tasknotification.view.home.tabtask.TaskViewModel
import com.example.tasknotification.view.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by nickevan on 13,October,2019
 */

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { AuthViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { DialogProjectViewmodel(get()) }
    viewModel { TaskViewModel(get()) }
    viewModel { AddTaskViewModel(get(), get()) }
}