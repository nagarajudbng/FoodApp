package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class Categories (

  @SerializedName("name"          ) var name          : String?                  = null,
  @SerializedName("subcategories" ) var subcategories : ArrayList<Subcategories> = arrayListOf()

)