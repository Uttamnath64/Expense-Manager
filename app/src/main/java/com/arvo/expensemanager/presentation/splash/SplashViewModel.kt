package com.arvo.expensemanager.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(){
    init {
        viewModelScope.launch {
            delay(1500)
        }
    }
}