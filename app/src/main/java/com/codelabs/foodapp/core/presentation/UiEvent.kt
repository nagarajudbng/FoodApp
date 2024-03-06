package com.codelabs.foodapp.core.presentation

import com.codelabs.foodapp.core.util.Event
import com.codelabs.foodapp.core.util.UiText

sealed class UiEvent: Event() {
    data class ShowSnackBar(val uiText:UiText):UiEvent()
    data class Navigate(val route:String):UiEvent()
    object NavigateUp:UiEvent()
    object OnLogin:UiEvent()
}