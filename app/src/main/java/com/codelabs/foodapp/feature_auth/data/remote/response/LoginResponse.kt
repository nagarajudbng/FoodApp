package com.codelabs.foodapp.feature_auth.data.remote.response

import com.google.gson.annotations.SerializedName


data class LoginResponse (

  @SerializedName("token" ) var token : String? = null

)