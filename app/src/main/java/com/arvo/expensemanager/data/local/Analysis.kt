package com.arvo.expensemanager.data.local

import androidx.room.Embedded
import com.arvo.expensemanager.domain.model.Entry

data class Analysis(
    val cashOutTotalThisMonth: Double,
    val cashInTotalThisMonth: Double,
    val cashOutTotalLastMonth: Double,
    val cashInTotalLastMonth: Double,
    val cash: Double,
    val upi: Double,
    val bank: Double,
    val total: Double
)