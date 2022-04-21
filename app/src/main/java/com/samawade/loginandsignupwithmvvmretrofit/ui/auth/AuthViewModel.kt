package com.samawade.loginandsignupwithmvvmretrofit.ui.auth

import androidx.lifecycle.ViewModel
import com.samawade.loginandsignupwithmvvmretrofit.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {
}