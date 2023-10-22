package com.arvo.expensemanager.presentation.book

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.widget.TabItem
import com.arvo.expensemanager.app.widget.Tabs
import com.arvo.expensemanager.app.widget.TabsContent
import com.arvo.expensemanager.app.widget.TopBarComposable
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
fun BookViewActivity(bookId: Int?, nevController: NavHostController) {

//    val tabs = listOf(
//        TabItem.Entity,
//        TabItem.Analysis,
//        TabItem.Category
//    )

    val tabs = listOf(
        TabItem.Entity,
        TabItem.Analysis,
//        TabItem.Category
    )
    val pagerState = com.google.accompanist.pager.rememberPagerState()


    Scaffold(
        topBar = {
            TopBarComposable(text = "View Book", nevController)
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, nevController,bookId)
        }
    }
}

@Preview
@Composable
fun BookViewActivityPreview(){
    ExpenseManagerTheme {
        Surface {
            BookViewActivity(0, rememberNavController()
            )
        }
    }
}