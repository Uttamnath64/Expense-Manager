package com.arvo.expensemanager.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arvo.expensemanager.presentation.book.BookViewActivity
import com.arvo.expensemanager.presentation.book.AddBookEntryActivity
import com.arvo.expensemanager.presentation.book.AddEditBookActivity
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
            AddEditBookActivity(nevController)
        }
        composable(
            Routes.BOOK_VIEW_SCREEN + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            if (id != null) {
                BookViewActivity(id, nevController)
            }
        }
        composable(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "/{id}/{type}",
            arguments = listOf(
                    navArgument("id"){ type = NavType.IntType},
                    navArgument("type"){ type = NavType.IntType}
                )
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val type = backStackEntry.arguments?.getInt("type")
            AddBookEntryActivity(id,type,nevController)
        }
    }
}