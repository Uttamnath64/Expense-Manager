package com.arvo.expensemanager.data.local

import androidx.room.Embedded
import androidx.room.Relation
import com.arvo.expensemanager.domain.model.Book

data class HomeBook(
    @Embedded val book: Book,
    val total: Double
)
