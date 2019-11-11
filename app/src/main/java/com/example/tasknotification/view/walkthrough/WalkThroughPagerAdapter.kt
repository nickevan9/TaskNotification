package com.example.tasknotification.view.walkthrough

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.tasknotification.view.walkthrough.task.TaskThroughFragment
import com.example.tasknotification.view.walkthrough.welcome.WelcomeThroughFragment
import com.example.tasknotification.view.walkthrough.work.WorkThroughFragment

class WalkThroughPagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WelcomeThroughFragment.newInstance()
            1 -> WorkThroughFragment.newInstance()
            2 -> TaskThroughFragment.newInstance()
            else -> WelcomeThroughFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}