package com.example.tasknotification.di

import com.example.tasknotification.data.prefs.PreferencesHelper
import com.example.tasknotification.data.prefs.PreferencesManager
import org.koin.dsl.module

/**
 * Created by nickevan on 17,October,2019
 */

val sharedPrefModule = module {
    single<PreferencesHelper> { PreferencesManager(get()) }
}