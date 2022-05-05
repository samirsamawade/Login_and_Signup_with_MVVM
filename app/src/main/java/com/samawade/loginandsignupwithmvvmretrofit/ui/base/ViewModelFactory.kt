package com.samawade.loginandsignupwithmvvmretrofit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.AuthRepository
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.BaseRepository
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.UserRepository
import com.samawade.loginandsignupwithmvvmretrofit.ui.auth.AuthViewModel
import com.samawade.loginandsignupwithmvvmretrofit.ui.home.HomeViewModel

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}