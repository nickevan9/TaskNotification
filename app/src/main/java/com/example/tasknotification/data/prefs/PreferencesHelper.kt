package com.example.tasknotification.data.prefs

interface PreferencesHelper {
    fun isFirstTime(): Boolean

    fun setFirstTime(firstTime: Boolean)

    fun setUser(user: String)

    fun getUser(): String

}