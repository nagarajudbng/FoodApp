package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class Images (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("type" ) var type : String? = null

)