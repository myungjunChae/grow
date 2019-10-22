package com.softdough.grow.presentation.routine.custom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Custom"
    }
    val text: LiveData<String> = _text
}