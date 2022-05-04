package com.samawade.loginandsignupwithmvvmretrofit.data.repository

import com.samawade.loginandsignupwithmvvmretrofit.data.UserPreferences
import com.samawade.loginandsignupwithmvvmretrofit.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences:UserPreferences
): BaseRepository() {
    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}