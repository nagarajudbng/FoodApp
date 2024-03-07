package com.codelabs.foodapp.feature_auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequestParent(
    @SerializedName("LoginRequest") val loginRequest: LoginRequest,
)