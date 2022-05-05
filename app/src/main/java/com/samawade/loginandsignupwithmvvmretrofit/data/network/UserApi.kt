package com.samawade.loginandsignupwithmvvmretrofit.data.network

import com.samawade.loginandsignupwithmvvmretrofit.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}