package com.alikizilcan.bankscase.infra.bases

import androidx.lifecycle.ViewModel
import com.alikizilcan.bankscase.infra.navigation.Navigation

abstract class BaseViewModel : ViewModel() {
    val baseNavigation = Navigation()
}