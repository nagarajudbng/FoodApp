package com.codelabs.foodapp

import com.google.gson.annotations.SerializedName


data class ItemList (

  @SerializedName("id"                   ) var id                   : String?           = null,
  @SerializedName("name"                 ) var name                 : String?           = null,
  @SerializedName("description"          ) var description          : String?           = null,
  @SerializedName("ingredients"          ) var ingredients          : String?           = null,
  @SerializedName("menutype"             ) var menutype             : String?           = null,
  @SerializedName("category"             ) var category             : String?           = null,
  @SerializedName("subcategory"          ) var subcategory          : String?           = null,
  @SerializedName("quantity"             ) var quantity             : String?           = null,
  @SerializedName("isHalfPlateEnabled"   ) var isHalfPlateEnabled   : String?           = null,
  @SerializedName("halfPlateQuantity"    ) var halfPlateQuantity    : String?           = null,
  @SerializedName("halfPlateDescription" ) var halfPlateDescription : String?           = null,
  @SerializedName("halfPlatePrice"       ) var halfPlatePrice       : Int?              = null,
  @SerializedName("price"                ) var price                : Int?              = null,
  @SerializedName("itemType"             ) var itemType             : String?           = null,
  @SerializedName("isAvailable"          ) var isAvailable          : Boolean?          = null,
  @SerializedName("images"               ) var images               : ArrayList<Images> = arrayListOf()

)