package com.example.tasknotification.view.auth.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.auth.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class ResetPasswordFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnChangePassword -> {
                if (mEdCode!!.text!!.isNotEmpty() && mEdPassword!!.text!!.isNotEmpty() && mEdCfPassword!!.text!!.isNotEmpty()) {
                    navController!!.navigate(R.id.action_resetPasswordFragment_to_successfulFragment)
                }
            }
            R.id.tvLogin -> {
                navController!!.navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }
        }
    }

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    private var mAuthViewModel: AuthViewModel? = null
    private var navController: NavController? = null

    private var mEdCode: TextInputEditText? = null
    private var mEdPassword: TextInputEditText? = null
    private var mEdCfPassword: TextInputEditText? = null
    private var mBtnChangePassword: Button? = null
    private var mTvLogin: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        changeStatusBarColor(activity!!,R.color.white)

        mEdCode = view.findViewById(R.id.edCode)
        mEdPassword = view.findViewById(R.id.edPassword)
        mEdCfPassword = view.findViewById(R.id.edCfPassword)
        mBtnChangePassword = view.findViewById(R.id.btnChangePassword)
        mTvLogin = view.findViewById(R.id.tvLogin)

        mBtnChangePassword!!.setOnClickListener(this)
        mTvLogin!!.setOnClickListener(this)

    }


}
