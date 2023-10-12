package com.arvo.expensemanager.app.widget

import androidx.compose.runtime.Composable
import com.arvo.expensemanager.presentation.page.page.AnalysisActivity
import com.arvo.expensemanager.presentation.page.page.CategoryActivity
import com.arvo.expensemanager.presentation.page.page.EntityActivity

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Entity : TabItem("Entity", { EntityActivity() })
    object Analysis : TabItem("Analysis", { AnalysisActivity() })
    object Category : TabItem("Category", { CategoryActivity() })
}