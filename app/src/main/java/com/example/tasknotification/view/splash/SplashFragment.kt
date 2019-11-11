package com.example.tasknotification.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    private val viewModel: SplashViewModel by viewModel()
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        mHandlerLeak.postDelayed({
            authenticationFragment(
                mPreferencesManager!!.isFirstTime(),
                mPreferencesManager!!.getUser()
            )

        }, 2000)

        changeStatusBarColor(activity!!, R.color.white)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun authenticationFragment(isFirstTime: Boolean, username: String) {
        when (isFirstTime) {
            false -> {
                if (username.isNotEmpty()) {
                    navController!!.navigate(R.id.action_splashFragment_to_homeActivity)
                } else {
                    navController!!.navigate(R.id.action_splashFragment_to_loginFragment)
                }

            }
            true -> {
                navController!!.navigate(R.id.action_splashFragment_to_walkThroughFragment)
            }
        }
    }

}
