package com.example.tasknotification.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.tasknotification.data.prefs.PreferencesManager
import com.example.tasknotification.helper.customview.showProgressDialog

/**
 * Created by nickevan on 13,October,2019
 */

abstract class BaseFragment : Fragment() {
    private var mProgressDialog: Dialog? = null
    val mHandlerLeak = Handler()
    var mPreferencesManager: PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    abstract fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mPreferencesManager = PreferencesManager(context!!)
        return setLayout(inflater, container, savedInstanceState)
    }

    fun showLoadingProgress() {
        mProgressDialog = showProgressDialog(context!!)

    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun hideKeyboard(ctx: Context) {
        val inputManager = ctx
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus:
        val v = (ctx as Activity).currentFocus ?: return

        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard(context!!)
        mProgressDialog = null
        mHandlerLeak.removeCallbacksAndMessages(null)
        mPreferencesManager = null
    }


}