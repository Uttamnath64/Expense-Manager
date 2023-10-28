package com.arvo.expensemanager.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
