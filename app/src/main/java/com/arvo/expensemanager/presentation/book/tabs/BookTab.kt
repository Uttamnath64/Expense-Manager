package com.arvo.expensemanager.presentation.book.tabs


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen300
import com.arvo.expensemanager.app.theme.colorRed300
import com.arvo.expensemanager.model.dto.PageDto.BookEntryStruct
import com.arvo.expensemanager.model.dto.PageDto.BookTabStruct
import com.arvo.expensemanager.presentation.Routes
import com.arvo.expensemanager.presentation.book.components.TableHeadingComposable
import com.arvo.expensemanager.presentation.book.components.TabsRowComposable
import com.arvo.expensemanager.presentation.book.viewModels.ViewBookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTab(
    nevController: NavController,
    bookId: Int,
    viewModel: ViewBookViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    var selectedItem by remember { mutableStateOf<Int>(1) }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    onClick = { nevController.navigate(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "?bookId=${bookId}&screenType=0") },
                    colors = ButtonDefaults.outlinedButtonColors(colorGreen300)
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Case IN",
                        style = ExpenseManagerTypography.titleSmall.copy(
                            color = ExpenseManagerColor.background
                        )
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    onClick = { nevController.navigate(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "?bookId=${bookId}&screenType=1") },
                    colors = ButtonDefaults.outlinedButtonColors(colorRed300)
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Case OUT",
                        style = ExpenseManagerTypography.titleSmall.copy(
                            color = ExpenseManagerColor.background
                        )
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(6.dp)
                .background(ExpenseManagerColor.background)
        ) {
            items(state.entryGroup) { item ->
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
                    Column {
                        TableHeadingComposable(item.id, selectedItem, item.datetime) {
                            selectedItem = it
                        }
                        AnimatedVisibility(visible = (selectedItem == item.id)) {
                            Column {
                                item.data.forEachIndexed { _, bookEntry ->
                                    TabsRowComposable(bookEntry) {
                                        nevController.navigate(Routes.ADD_EDIT_BOOK_ENTRY_SCREEN + "?bookId=${bookId}&screenType=2&entryId=${bookEntry.id}")
                                    }
                                }
                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    shape = RoundedCornerShape(0.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),

                                    ) {
                                    Text(
                                        text = "View More",
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookTabPreview() {
    BookTab(rememberNavController(), 0)
}