package com.nawaf.example.randomquote.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {

    /**
     * This event for passing a message to be passed to the activity to be shown as (Popup/Toast ..)
     */
    private val _showMsgEvent = MutableLiveData<Event<String>>()
    val showMsgEvent: LiveData<Event<String>> = _showMsgEvent

    protected fun showMessage(text: String) {
        _showMsgEvent.value = Event(text)
    }


}