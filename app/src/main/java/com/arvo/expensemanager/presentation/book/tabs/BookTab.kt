package com.arvo.expensemanager.presentation.book.tabs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen300

@Composable
fun BookTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(ExpenseManagerColor.background)
    ) {
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            LazyColumn(
            ){
                item {
                    TableColumn()
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            LazyColumn(
            ){
                item {
                    TableColumn()
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            LazyColumn(
            ){
                item {
                    TableColumn()
                }
            }
        }
    }
}
@Composable
fun TableColumn(){
    var isRotated by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (isRotated) 270f else 90f, label = "")
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { isRotated = !isRotated },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp,8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.4f),
            ) {
                Text(
                    text = "18 Aug, 2023",
                    style = ExpenseManagerTypography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(.4f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Case in/out",
                    style = ExpenseManagerTypography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Balance",
                            style = ExpenseManagerTypography.labelLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "970",
                            style = ExpenseManagerTypography.labelLarge.copy(
                                color = colorGreen300,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        modifier = Modifier
                            .rotate(rotationState)
                            .size(28.dp)
                            .padding(6.dp),
                        tint = ExpenseManagerColor.outline
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BookTabPreview() {
    BookTab()
}