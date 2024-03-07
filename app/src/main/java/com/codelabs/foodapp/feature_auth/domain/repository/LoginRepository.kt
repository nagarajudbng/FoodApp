package com.codelabs.foodapp.feature_auth.domain.repository

import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.feature_auth.domain.model.LoginResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(
        email:String,
        password:String
    ): SimpleResource
}