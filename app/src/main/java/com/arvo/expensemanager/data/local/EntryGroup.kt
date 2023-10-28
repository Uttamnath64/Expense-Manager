package com.arvo.expensemanager.data.local

import androidx.room.Embedded
import com.arvo.expensemanager.domain.model.Entry

data class EntryGroup(
    val id: Int,
    val data: List<Entry>,
    val datetime: Long,
    val total: Double
)