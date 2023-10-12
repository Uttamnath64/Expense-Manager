package com.arvo.expensemanager.model.dto.PageDto

data class PageDto(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val isUpdated: Boolean,
    val amount: Double
)
