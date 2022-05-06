package com.samawade.loginandsignupwithmvvmretrofit.ui.base

import androidx.lifecycle.ViewModel
import com.samawade.loginandsignupwithmvvmretrofit.data.network.UserApi
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}