package com.codelabs.foodapp.core.data.dto

data class BasicApiResponse<T> (
    val successful:Boolean,
    val message:String?=null,
    val data:T?=null
)