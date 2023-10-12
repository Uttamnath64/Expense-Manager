package com.arvo.expensemanager.app.widget

import androidx.compose.runtime.Composable
import com.arvo.expensemanager.presentation.book.tabs.AnalysisTab
import com.arvo.expensemanager.presentation.book.tabs.BookTab
import com.arvo.expensemanager.presentation.book.tabs.CategoryTab

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Entity : TabItem("Entry", { BookTab() })
    object Analysis : TabItem("Analysis", { AnalysisTab() })
    object Category : TabItem("Category", { CategoryTab() })
}