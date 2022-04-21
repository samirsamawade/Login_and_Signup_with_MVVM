package com.samawade.loginandsignupwithmvvmretrofit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samawade.loginandsignupwithmvvmretrofit.repository.AuthRepository
import com.samawade.loginandsignupwithmvvmretrofit.repository.BaseRepository
import com.samawade.loginandsignupwithmvvmretrofit.ui.auth.AuthViewModel

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}