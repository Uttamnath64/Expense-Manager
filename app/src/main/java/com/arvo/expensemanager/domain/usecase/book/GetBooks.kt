package com.arvo.expensemanager.domain.usecase.book

import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.repository.BookRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBooks(
    private val repository: BookRepository
) {
    operator fun invoke(
        orderType: OrderType = OrderType.Ascending
    ): Flow<List<HomeBook>>{
        return repository.getBooks().map { books ->
            when(orderType){
                is OrderType.Ascending -> books.sortedBy { it.book.timestamp }
                is OrderType.Descending -> books.sortedByDescending { it.book.timestamp }
                else -> {books}
            }
        }
    }

}