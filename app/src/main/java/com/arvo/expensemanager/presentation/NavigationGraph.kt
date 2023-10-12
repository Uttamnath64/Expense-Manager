package com.arvo.expensemanager.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.presentation.book.BookViewActivity
import com.arvo.expensemanager.presentation.home.AddBookActivity
import com.arvo.expensemanager.presentation.home.HomeActivity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(){
    val nevController = rememberNavController()

    NavHost(navController = nevController, startDestination = Routes.HOME_SCREEN){
        composable(Routes.HOME_SCREEN){
            HomeActivity(nevController)
        }
        composable(Routes.ADD_BOOK_SCREEN){
            AddBookActivity({},nevController)
        }
        composable(Routes.BOOK_VIEW_SCREEN){
            BookViewActivity({},nevController)
        }
    }
}