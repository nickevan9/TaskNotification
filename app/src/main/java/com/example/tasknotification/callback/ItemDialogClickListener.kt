package com.example.tasknotification.callback

import com.example.tasknotification.data.local.Project

/**
 * Created by nickevan on 18,October,2019
 */
interface ItemDialogClickListener {
    fun onItemClick(project: Project)
}