package com.example.tasknotification.view.auth.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.auth.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSendRequest -> {
                if (mEdEmail!!.text!!.isNotEmpty()) {
                    navController!!.navigate(R.id.action_forgotFragment_to_resetPasswordFragment)
                }
            }
            R.id.tvLogin -> {
                navController!!.navigate(R.id.action_forgotFragment_to_loginFragment)
            }
        }
    }

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    private val mAuthViewModel: AuthViewModel by viewModel()
    private var navController: NavController? = null

    private var mEdEmail: TextInputEditText? = null
    private var mBtnRequest: Button? = null
    private var mTvLogin: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeStatusBarColor(activity!!,R.color.white)
        navController = Navigation.findNavController(view)

        mEdEmail = view.findViewById(R.id.edEmail)
        mBtnRequest = view.findViewById(R.id.btnSendRequest)
        mTvLogin = view.findViewById(R.id.tvLogin)

        mBtnRequest!!.setOnClickListener(this)
        mTvLogin!!.setOnClickListener(this)


    }

}
