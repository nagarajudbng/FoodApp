package com.codelabs.foodapp.feature_auth.data.remote

import com.codelabs.foodapp.feature_auth.data.remote.request.LoginRequestParent
import com.codelabs.foodapp.feature_auth.data.remote.response.LoginResponseParent
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    companion object {
        const val BASE_URL = "http://ec2-3-111-157-50.ap-south-1.compute.amazonaws" +
                ".com:8080/highwayhelper/"
    }
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequestParent):Response<JSONObject>
}