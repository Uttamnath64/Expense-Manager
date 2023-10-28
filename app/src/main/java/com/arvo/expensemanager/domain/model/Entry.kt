package com.arvo.expensemanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Entry(
    @PrimaryKey val id: Int? = null,
    val bookId: Int,
    val title: String,
    val description: String,
    val amount: Double,
    val paymentType: Boolean,
    val paymentMethod: Int,
    val timestamp: Long
)