@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.arvo.expensemanager.presentation.book.tabs


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.data.model.dto.book.BookEntryStruct
import com.arvo.expensemanager.data.model.dto.book.BookTabStruct
import com.arvo.expensemanager.presentation.book.helper.TableHeading
import com.arvo.expensemanager.presentation.book.helper.TabsBody

@Composable
fun BookTab() {

    var bookData: List<BookTabStruct>
    bookData = ArrayList<BookTabStruct>()

    bookData = listOf(
        BookTabStruct(
            "Aug 15, 2023",
            800.0,
            listOf(
                BookEntryStruct(
                    1,
                    "Enrty 1",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    2,
                    "Enrty 2",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    3,
                    "Enrty 3",
                    -30.0,
                    400.0
                )
            )
        ),
        BookTabStruct(
            "Aug 15, 2023",
            800.0,
            listOf(
                BookEntryStruct(
                    1,
                    "Enrty 1",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    2,
                    "Enrty 2",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    3,
                    "Enrty 3",
                    -30.0,
                    400.0
                )
            )
        ),
        BookTabStruct(
            "Aug 15, 2023",
            800.0,
            listOf(
                BookEntryStruct(
                    1,
                    "Enrty 1",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    2,
                    "Enrty 2",
                    -30.0,
                    400.0
                ),
                BookEntryStruct(
                    3,
                    "Enrty 3",
                    -30.0,
                    400.0
                )
            )
        )
    )

    var selectedItem by remember { mutableStateOf<Int?>(null) }

    val current = LocalContext.current

    val isClicked =  selectedItem != null

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(ExpenseManagerColor.background)
    ) {
        items(bookData) {item ->
            val itemIndex = bookData.indexOf(item)
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
                Column{
                    TableHeading(itemIndex, selectedItem, item.date) {
                        selectedItem = it
                        Toast.makeText(current,itemIndex.toString(),Toast.LENGTH_LONG).show()
                    }
                    item.bookEntryStruct.forEachIndexed { _, bookEntry ->
                        val size = item.bookEntryStruct.size
                        TabsBody(current, bookEntry, selectedItem == itemIndex)
                        if (item.bookEntryStruct.indexOf(bookEntry) === size - 1) {
                            if (selectedItem == itemIndex) {
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
    BookTab()
}