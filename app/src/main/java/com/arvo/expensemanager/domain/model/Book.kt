package com.arvo.expensemanager.domain.model

import androidx.room.PrimaryKey
import java.sql.Timestamp

data class Book(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val timestamp: Long
)