package com.example.tasknotification.callback

import java.text.ParsePosition

/**
 * Created by nickevan on 15,October,2019
 */
interface TaskCallBackListener {
    fun ignoreTask(position: Int)

    fun detailTask(position: Int)

    fun checkTask(position: Int)
}