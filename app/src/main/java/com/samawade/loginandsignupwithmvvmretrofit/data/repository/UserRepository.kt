package com.samawade.loginandsignupwithmvvmretrofit.data.repository

import com.samawade.loginandsignupwithmvvmretrofit.data.network.UserApi

class UserRepository(
    private val api: UserApi,
): BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}