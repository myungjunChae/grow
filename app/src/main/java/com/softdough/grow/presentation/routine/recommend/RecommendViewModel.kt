package com.softdough.grow.presentation.routine.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecommendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Recommend"
    }
    val text: LiveData<String> = _text
}