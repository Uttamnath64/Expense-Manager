package com.arvo.expensemanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Book(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val timestamp: Long
)