package com.codelabs.foodapp.util.test

import com.codelabs.foodapp.feature_auth.data.remote.response.LoginResponseParent
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun registrationPost(@Body request: JSONObject): Call<JSONObject>
}