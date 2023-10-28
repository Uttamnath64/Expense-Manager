package com.arvo.expensemanager.domain.usecase.book

import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.repository.BookRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBook(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: Int): Book?{
        return repository.getBook(id)
    }
}