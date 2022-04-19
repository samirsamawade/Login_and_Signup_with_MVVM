package com.samawade.loginandsignupwithmvvmretrofit.repository

import com.samawade.loginandsignupwithmvvmretrofit.network.AuthApi

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