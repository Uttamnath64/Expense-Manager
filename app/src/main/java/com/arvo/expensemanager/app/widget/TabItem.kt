package com.arvo.expensemanager.app.widget

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.arvo.expensemanager.presentation.book.tabs.AnalysisTab
import com.arvo.expensemanager.presentation.book.tabs.BookTab
import com.arvo.expensemanager.presentation.book.tabs.CategoryTab

//sealed class TabItem(var title: String, var screen: @Composable () -> Unit) {
//    object Entity : TabItem("Entry", { BookTab() })
//    object Analysis : TabItem("Analysis", { AnalysisTab() })
//    object Category : TabItem("Category", { CategoryTab() })
//}

sealed class TabItem(var title: String, var screen: @Composable (NavController,Int) -> Unit) {
    object Entity : TabItem("Entry", { navController,bookId -> BookTab(navController,bookId) })
    object Analysis : TabItem("Analysis", { navController,bookId -> AnalysisTab(navController,bookId) })
    object Category : TabItem("Category", { navController,bookId -> CategoryTab(navController,bookId) })
}
