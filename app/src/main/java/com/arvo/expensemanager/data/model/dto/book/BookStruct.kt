package com.arvo.expensemanager.data.model.dto.book

data class BookTabStruct(
    val id: Int,
    val date: String,
    val amount: Double,
    val bookEntryStruct: List<BookEntryStruct>
)
data class BookEntryStruct(
    val id: Int,
    val title: String,
    val case: Double,
    val amount: Double
)
