package com.alikizilcan.bankscase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
    var isConnectionAvailable : MutableLiveData<Boolean> = MutableLiveData()
}