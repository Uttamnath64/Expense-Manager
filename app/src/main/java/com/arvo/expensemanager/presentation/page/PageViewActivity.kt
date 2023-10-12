package com.arvo.expensemanager.presentation.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.widget.TabItem
import com.arvo.expensemanager.app.widget.Tabs
import com.arvo.expensemanager.app.widget.TabsContent
import com.arvo.expensemanager.app.widget.TopBarComposable
import com.arvo.expensemanager.presentation.page.page.AnalysisActivity
import com.arvo.expensemanager.presentation.page.page.CategoryActivity
import com.arvo.expensemanager.presentation.page.page.EntityActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
fun PageViewActivity(onItemCreate: (Boolean) -> Unit, nevController: NavHostController) {

    val tabs = listOf(
        TabItem.Entity,
        TabItem.Analysis,
        TabItem.Category
    )
    val pagerState = com.google.accompanist.pager.rememberPagerState()


    Scaffold(
        topBar = {
            TopBarComposable(text = "Create Page", nevController)
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Preview
@Composable
fun PageViewActivityP(){
    ExpenseManagerTheme {
        Surface {
            PageViewActivity({

            }, rememberNavController()
            )
        }
    }
}