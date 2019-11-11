package com.example.tasknotification.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasknotification.R
import com.example.tasknotification.callback.ItemDialogClickListener
import com.example.tasknotification.data.local.Project

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
