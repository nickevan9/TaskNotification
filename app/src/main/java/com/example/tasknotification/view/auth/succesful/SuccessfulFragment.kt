package com.example.tasknotification.view.auth.succesful


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class SuccessfulFragment : BaseFragment() {
    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_succesful, container, false)
    }

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        changeStatusBarColor(activity!!, R.color.white)

        mHandlerLeak.postDelayed({
            navController!!.navigate(R.id.action_successfulFragment_to_loginFragment)

        }, 2000)
    }

}
