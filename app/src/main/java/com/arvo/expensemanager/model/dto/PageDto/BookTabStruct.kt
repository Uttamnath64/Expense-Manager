package com.arvo.expensemanager.model.dto.PageDto

data class BookTabStruct(
    val id: Int,
    val date: String,
    val total: Double,
    val bookEntryStruct: List<BookEntryStruct>
)

data class BookEntryStruct(
    val id: Int,
    val title: String,
    val amount: Double,
    val total: Double,
)
