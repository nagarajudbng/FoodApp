package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class MenuItemList (

  @SerializedName("categories" ) var categories : ArrayList<Categories> = arrayListOf()

)