package com.samawade.loginandsignupwithmvvmretrofit.data.repository

import com.samawade.loginandsignupwithmvvmretrofit.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository() {
    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }
}