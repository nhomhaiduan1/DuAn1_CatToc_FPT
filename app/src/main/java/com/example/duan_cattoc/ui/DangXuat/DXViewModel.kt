package com.example.duan_cattoc.ui.DangXuat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DXViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dx Fragment"
    }
    val text: LiveData<String> = _text
}