package com.codelabs.foodapp.feature_auth.domain.usecases

import AuthModule
import android.content.Context
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.feature_auth.data.repository.LoginRepositoryImpl
import com.codelabs.foodapp.feature_auth.domain.model.LoginResult
import com.codelabs.foodapp.feature_auth.presentation.util.AuthError

class LoginUseCase(private val app: Context) {

    private val repository:LoginRepositoryImpl
    init {
        repository = LoginRepositoryImpl(AuthModule.getApi(app),AuthModule.getSharedPref(app))
    }
    suspend operator fun invoke(email:String,password:String):LoginResult{
        val emailError = if(email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if(password.isBlank()) AuthError.FieldEmpty else null

        if(emailError !=null || passwordError !=null){
            return LoginResult(emailError, passwordError)
        }
//        repository.login(email,password)
//        return LoginResult(null,null,
//            Resource.Success(Unit) as SimpleResource)
        return LoginResult(
            result = repository.login(email,password)
        )

    }
}