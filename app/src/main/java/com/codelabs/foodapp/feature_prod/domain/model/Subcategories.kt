package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class Subcategories (

  @SerializedName("name"     ) var name     : String?             = null,
  @SerializedName("itemList" ) var itemList : ArrayList<ItemList> = arrayListOf()

)