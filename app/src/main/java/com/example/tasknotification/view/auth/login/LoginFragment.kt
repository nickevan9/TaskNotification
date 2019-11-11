package com.example.tasknotification.view.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.auth.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(), View.OnClickListener {

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    private val mLoginViewModel: AuthViewModel by viewModel()
    private var navController: NavController? = null

    private var mTvFingerPrint: TextView? = null
    private var mEdUserName: TextInputEditText? = null
    private var mEdPassword: TextInputEditText? = null
    private var mTvForgotPassword: TextView? = null
    private var mTvRegister: TextView? = null
    private var mBtnLogin: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        changeStatusBarColor(activity!!, R.color.white)

        mTvFingerPrint = view.findViewById(R.id.tvFingerPrint)
        mEdUserName = view.findViewById(R.id.edUsername)
        mEdPassword = view.findViewById(R.id.edPassword)
        mTvForgotPassword = view.findViewById(R.id.tvForgot)
        mTvRegister = view.findViewById(R.id.tvRegister)
        mBtnLogin = view.findViewById(R.id.btnLogin)

        mTvFingerPrint!!.setOnClickListener(this)
        mTvForgotPassword!!.setOnClickListener(this)
        mTvRegister!!.setOnClickListener(this)
        mBtnLogin!!.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvFingerPrint -> {
                Toast.makeText(context, "Access finger to login", Toast.LENGTH_SHORT).show()
            }
            R.id.tvForgot -> {
                navController!!.navigate(R.id.action_loginFragment_to_forgotFragment)
            }
            R.id.tvRegister -> {
//                navController!!.navigate(R.id.action_loginFragment_to_registerFragment)
                navController!!.navigate(R.id.action_loginFragment_to_registerFragment)

            }
            R.id.btnLogin -> {
                if (mEdUserName!!.text!!.isNotEmpty() && mEdPassword!!.text!!.isNotEmpty()) {
                    mPreferencesManager!!.setUser(mEdUserName!!.text.toString())
                    navController!!.navigate(R.id.action_loginFragment_to_homeActivity)
                } else {
                    Toast.makeText(context, "Enter username and password", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }


}
