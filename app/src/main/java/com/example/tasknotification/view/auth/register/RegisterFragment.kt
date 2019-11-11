package com.example.tasknotification.view.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasknotification.R
import com.example.tasknotification.helper.customview.changeStatusBarColor
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.auth.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvWithOut -> {

            }
            R.id.tvLogin -> {
                navController!!.navigate(R.id.action_registerFragment_to_loginFragment)
            }
            R.id.btnRegister -> {
                if (mEdUserName!!.text!!.isNotEmpty() && mEdPassword!!.text!!.isNotEmpty() && mEdEmail!!.text!!.isNotEmpty()) {
                    navController!!.navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }
    }

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    private val mAuthViewModel: AuthViewModel by viewModel()
    private var navController: NavController? = null

    private var mTvWithoutLimit: TextView? = null
    private var mEdUserName: TextInputEditText? = null
    private var mEdPassword: TextInputEditText? = null
    private var mEdEmail: TextInputEditText? = null
    private var mTvLogin: TextView? = null
    private var mBtnRegister: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        changeStatusBarColor(activity!!,R.color.white)

        mTvWithoutLimit = view.findViewById(R.id.tvWithOut)
        mEdUserName = view.findViewById(R.id.edUsername)
        mEdPassword = view.findViewById(R.id.edPassword)
        mEdEmail = view.findViewById(R.id.edEmail)
        mTvLogin = view.findViewById(R.id.tvLogin)
        mBtnRegister = view.findViewById(R.id.btnRegister)

        mTvWithoutLimit!!.setOnClickListener(this)
        mTvLogin!!.setOnClickListener(this)
        mBtnRegister!!.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
