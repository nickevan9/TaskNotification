package com.example.tasknotification.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by nickevan on 15,October,2019
 */

@Parcelize
data class User(
    var id: String,
    var username: String,
    var password: String,
    var avatar: String,
    var totalTask: Int,
    var completeTask: Int,
    var archiveTask: Int,
    var task: Task

) : Parcelable