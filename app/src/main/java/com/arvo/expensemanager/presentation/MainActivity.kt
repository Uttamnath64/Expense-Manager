package com.arvo.expensemanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme{
                ExpenseManager()
            }
        }
    }
}


@Composable
fun ExpenseManager() {
    ExpenseManagerNavigationGraph()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseManagerTheme{
        ExpenseManager()
    }
}
