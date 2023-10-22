package com.arvo.expensemanager.domain.model

import androidx.room.PrimaryKey
import java.sql.Timestamp

data class Entry(
    @PrimaryKey val id: Int? = null,
    val book: Book,
    val title: String,
    val description: String,
    val amount: Double,
    val paymentType: Int,
    val timestamp: Long
)