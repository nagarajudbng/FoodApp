package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class MenuListResonse (

  @SerializedName("code"         ) var code         : String?       = null,
  @SerializedName("businessId"   ) var businessId   : String?       = null,
  @SerializedName("menuItemList" ) var menuItemList : MenuItemList? = MenuItemList(),
  @SerializedName("message"      ) var message      : String?       = null

)