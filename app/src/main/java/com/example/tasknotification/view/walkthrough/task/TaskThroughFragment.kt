package com.example.tasknotification.view.walkthrough.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tasknotification.R

class TaskThroughFragment : Fragment() {

    companion object {
        fun newInstance() = TaskThroughFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_through, container, false)
    }


}
