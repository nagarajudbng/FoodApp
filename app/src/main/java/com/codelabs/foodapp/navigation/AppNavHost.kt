package com.codelabs.foodapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codelabs.foodapp.feature_auth.presentation.login.LoginScreen
import com.codelabs.foodapp.feature_prod.presentation.MenuScreen
import com.codelabs.foodapp.feature_auth.domain.usecases.LoginUseCase
import com.codelabs.foodapp.feature_auth.presentation.login.LoginViewModel
import com.codelabs.foodapp.feature_prod.presentation.categories


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route
){
    NavHost(modifier = modifier,
        navController = navController,
        startDestination = startDestination){
        composable(NavigationItem.Login.route){
            LoginScreen(navController,LoginViewModel(LoginUseCase()))
        }
        composable(NavigationItem.Menu.route){
            MenuScreen(navController, categories)
        }
    }
}



