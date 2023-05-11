package com.kkc.githubusers.util

import androidx.lifecycle.Observer

class SingleEventObserver<T> (private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {
    override fun onChanged(singleEvent: SingleEvent<T>?) {
        singleEvent?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}