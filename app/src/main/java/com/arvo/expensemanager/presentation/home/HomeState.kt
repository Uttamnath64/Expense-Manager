package com.arvo.expensemanager.presentation.home

import com.arvo.expensemanager.data.local.HomeBook

data class HomeState(
    val homeBooks: List<HomeBook> = emptyList()
)
