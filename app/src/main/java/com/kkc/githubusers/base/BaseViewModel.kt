package com.kkc.githubusers.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kkc.githubusers.util.SingleEvent

open class BaseViewModel : ViewModel() {
    protected val _progressVisible = MutableLiveData(false)
    val progressVisible: LiveData<Boolean>
        get() = _progressVisible

    protected val _toastText = MutableLiveData<SingleEvent<String>>()
    val toastText: LiveData<SingleEvent<String>>
        get() = _toastText

    protected val _toastRes = MutableLiveData<SingleEvent<Int>>()
    val toastRes: LiveData<SingleEvent<Int>>
        get() = _toastRes
}
