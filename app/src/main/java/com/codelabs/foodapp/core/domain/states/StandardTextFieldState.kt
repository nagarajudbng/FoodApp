package com.codelabs.foodapp.core.domain.states

data class StandardTextFieldState(
    val text:String = "",
    val error:Error? = null

)