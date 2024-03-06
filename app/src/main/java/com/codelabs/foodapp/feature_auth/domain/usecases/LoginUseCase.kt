package com.codelabs.foodapp.feature_auth.domain.usecases

import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.feature_auth.domain.model.LoginResult
import com.codelabs.foodapp.feature_auth.presentation.util.AuthError

class LoginUseCase {
    suspend operator fun invoke(email:String,password:String):LoginResult{
        val emailError = if(email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if(password.isBlank()) AuthError.FieldEmpty else null

        if(emailError !=null || passwordError !=null){
            return LoginResult(emailError, passwordError)
        }

        return LoginResult(null,null,
            Resource.Success(Unit) as SimpleResource
        )

    }
}