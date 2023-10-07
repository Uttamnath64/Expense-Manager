package com.arvo.expensemanager.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.presentation.home.HomeActivity
import com.arvo.expensemanager.presentation.splash.SplashActivity

@Composable
fun ExpenseManagerNavigationGraph(){
    val nevController = rememberNavController()

    NavHost(navController = nevController, startDestination = Routes.SPLASH_SCREEN){
        composable(Routes.SPLASH_SCREEN){
            SplashActivity(nevController)
        }
        composable(Routes.HOME_SCREEN){
            HomeActivity(nevController)
        }
    }
}