package com.codelabs.foodapp.navigation

enum class Screen{
    LOGIN,
    MENU
}
sealed class NavigationItem(val route:String){
    data object Login: NavigationItem(Screen.LOGIN.name)
    data object Menu: NavigationItem(Screen.MENU.name)
}