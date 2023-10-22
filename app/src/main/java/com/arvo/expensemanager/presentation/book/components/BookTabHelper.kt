package com.arvo.expensemanager.presentation.book.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen300
import com.arvo.expensemanager.model.dto.PageDto.BookEntryStruct
import com.arvo.expensemanager.presentation.Routes

@Composable
fun TableHeadingComposable(index: Int,selectedItem: Int?, date: String,onClick: (Int) -> Unit) {
    val isClicked =  selectedItem == index

    val rotationState by animateFloatAsState(targetValue = if (isClicked) 90f else 270f, label = "")
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(if(isClicked) 0 else index)},
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp, 8.dp, 12.dp, 8.dp)
                .drawBehind {
                    if (isClicked) {
                        val strokeWidth = 1.dp.toPx()
                        val y = size.height - strokeWidth / 2

                        drawLine(
                            Color.LightGray,
                            start = Offset(2f, y),
                            end = Offset(size.width - 2f, y),
                            strokeWidth
                        )
                    }
                }
                .padding(bottom = if (isClicked) 6.dp else 0.dp),
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
                            .padding(7.dp),
                        tint = ExpenseManagerColor.outline
                    )
                }
            }
        }
    }
}

@Composable
fun TabsRowComposable(data: BookEntryStruct, nevController: NavController, bookId: Int) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { nevController.navigate(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "/$bookId/1") },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.4f),
            ) {
                Text(
                    text = data.title,
                    style = ExpenseManagerTypography.labelLarge
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(.4f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = data.toString(),
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
                    Text(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        text = data.amount.toString(),
                        style = ExpenseManagerTypography.labelLarge.copy(
                            color = colorGreen300,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        modifier = Modifier
                            .rotate(180f)
                            .size(28.dp)
                            .padding(7.dp),
                        tint = ExpenseManagerColor.outline
                    )
                }
            }
        }
    }
}