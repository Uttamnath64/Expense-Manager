package com.arvo.expensemanager.presentation.book.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor

@Composable
fun CategoryTab(nevController: NavController, bookId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ExpenseManagerColor.background)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Category View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryTabPreview() {
    CategoryTab(rememberNavController(), 0)
}