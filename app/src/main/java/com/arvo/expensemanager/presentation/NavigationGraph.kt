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
        composable(Routes.ADD_BOOK_SCREEN + "?bookId={bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ){
            AddEditBookActivity(nevController)
        }
        composable(
            Routes.BOOK_VIEW_SCREEN + "?bookId={bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ){ backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1
            BookViewActivity(bookId,nevController)
        }
        composable(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "?bookId={bookId}&screenType={screenType}&entryId={entryId}",
            arguments = listOf(
                    navArgument("bookId"){
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument("screenType"){
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument("entryId"){
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
        ){
            AddBookEntryActivity(nevController)
        }
    }
}