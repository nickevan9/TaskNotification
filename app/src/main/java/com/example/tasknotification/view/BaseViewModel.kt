package com.example.tasknotification.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasknotification.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * Created by nickevan on 16,October,2019
 */

abstract class BaseViewModel : ViewModel() {
    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()
    val statusDB = MutableLiveData<String>()

    val exceptionHandler: CoroutineContext =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            showLoading.value = false
            showError.value = throwable.message.toString()
        }

}