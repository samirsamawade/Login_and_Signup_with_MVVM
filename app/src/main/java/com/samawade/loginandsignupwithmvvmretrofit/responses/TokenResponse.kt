package com.samawade.loginandsignupwithmvvmretrofit.responses

data class TokenResponse(
    val access_token: String?,
    val refresh_token: String?
)