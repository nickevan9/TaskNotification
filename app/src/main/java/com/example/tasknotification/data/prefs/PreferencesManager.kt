package com.example.tasknotification.data.prefs

import android.content.Context


const val PREF_KEY_FIRST_TIME = "PREF_KEY_FIRST_TIME"
const val PREF_KEY_USER = "PREF_KEY_USER"

class PreferencesManager(context: Context) : PreferencesHelper {

    private val sharedPreferences = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )

    override fun setFirstTime(firstTime: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(PREF_KEY_FIRST_TIME, firstTime)
        editor.apply()
    }

    override fun isFirstTime(): Boolean {
        return sharedPreferences.getBoolean(PREF_KEY_FIRST_TIME, true)
    }


    override fun setUser(user: String) {
        val editor = sharedPreferences.edit()
        editor.putString(PREF_KEY_USER, user)
        editor.apply()
    }

    override fun getUser(): String {
        return sharedPreferences.getString(PREF_KEY_USER, "")!!
    }

}