package com.codelabs.foodapp.feature_auth.domain.model

import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.feature_auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? =null,
    val passwordError: AuthError?=null,
    val result:SimpleResource?=null
)