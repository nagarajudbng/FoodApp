package com.codelabs.foodapp.core.domain.states

data class PaswordFieldState (
    val text:String = "",
    val error:Error? = null,
    val isPasswordVisible:Boolean = false
)
