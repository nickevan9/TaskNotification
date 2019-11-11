package com.example.tasknotification.view.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager

import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class WalkThroughFragment : BaseFragment() {
    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.walk_through_fragment, container, false)
    }


    private val viewModel: WalkThroughViewModel by viewModel()
    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null
    private var mTvLogin: TextView? = null
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeStatusBarColor(activity!!, R.color.white)
        navController = Navigation.findNavController(view)
        mTvLogin = view.findViewById(R.id.tvLogin)
        mTabLayout = view.findViewById(R.id.tabDots)
        mViewPager = view.findViewById(R.id.vPWalkThrough)
        mTabLayout!!.setupWithViewPager(mViewPager, true)

        mTabLayout!!.addTab(mTabLayout!!.newTab())
        mTabLayout!!.addTab(mTabLayout!!.newTab())
        mTabLayout!!.addTab(mTabLayout!!.newTab())

        val mAdapter = WalkThroughPagerAdapter(childFragmentManager, mTabLayout!!.tabCount)
        mViewPager!!.adapter = mAdapter

        mViewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))

        mTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        mTvLogin!!.setOnClickListener {
            mPreferencesManager!!.setFirstTime(false)
            if (navController!!.currentDestination?.id == R.id.walkThroughFragment) {
                navController!!.navigate(R.id.action_walkThroughFragment_to_loginFragment)
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
