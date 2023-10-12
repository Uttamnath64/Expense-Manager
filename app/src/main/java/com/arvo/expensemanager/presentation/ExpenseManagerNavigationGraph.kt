package com.arvo.expensemanager.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.presentation.home.CreatePageActivity
import com.arvo.expensemanager.presentation.home.HomeActivity
import com.arvo.expensemanager.presentation.page.PageViewActivity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseManagerNavigationGraph(){
    val nevController = rememberNavController()

    NavHost(navController = nevController, startDestination = Routes.HOME_SCREEN){
        composable(Routes.HOME_SCREEN){
            HomeActivity(nevController)
        }
        composable(Routes.PAGE_ADD_SCREEN){
            CreatePageActivity({},nevController)
        }
        composable(Routes.PAGE_VIEW_SCREEN){
            PageViewActivity({},nevController)
        }
    }
}