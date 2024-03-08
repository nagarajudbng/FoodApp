package com.codelabs.foodapp.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codelabs.foodapp.R
import com.codelabs.foodapp.feature_auth.presentation.login.LoginScreen
import com.codelabs.foodapp.feature_prod.presentation.MenuScreen
import com.codelabs.foodapp.feature_auth.domain.usecases.LoginUseCase
import com.codelabs.foodapp.feature_auth.presentation.login.LoginViewModel
import com.codelabs.foodapp.feature_prod.domain.usecases.ProductUseCase
import com.codelabs.foodapp.feature_prod.presentation.MenuViewModel
import com.codelabs.foodapp.feature_prod.presentation.categories
import kotlinx.coroutines.Dispatchers


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination){
        composable(NavigationItem.Login.route){
            LoginScreen(navController,LoginViewModel(LoginUseCase(LocalContext.current
                .applicationContext)))
        }
        composable(NavigationItem.Menu.route){
            MenuScreen(
                modifier = Modifier.background(colorResource(id = R.color.list_background)),
            navController,
                MenuViewModel(
                        ProductUseCase(
                            LocalContext.current.applicationContext
                        ),
                        Dispatchers.Default
                    ),
                    categories)
        }
    }
}



