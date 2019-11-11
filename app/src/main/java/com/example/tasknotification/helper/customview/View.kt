package com.example.tasknotification.helper.customview

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.InputType
import android.view.View
import android.view.Window
import com.example.tasknotification.R
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat
import android.view.WindowManager
import android.widget.EditText


/**
 * Created by nickevan on 13,October,2019
 */

fun View.showSnackbar(
    msg: String
) {
    val snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snackbar.setActionTextColor(Color.RED)
    snackbar.show()

}

fun showProgressDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    if (dialog.window != null) {
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    dialog.setContentView(R.layout.progress_dialog)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()

    return dialog

}

fun changeStatusBarColor(activity: Activity, color: Int) {
    val window = activity.window

// clear FLAG_TRANSLUCENT_STATUS flag:
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

// finally change the color
    window.setStatusBarColor(ContextCompat.getColor(activity, color))
}

fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !value
    isFocusableInTouchMode = !value
    this.inputType = inputType
}