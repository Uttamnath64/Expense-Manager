package com.arvo.expensemanager.domain.usecase.book

import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.repository.BookRepository

class InsertBook(
    private val repository: BookRepository
) {
    suspend operator fun invoke(book: Book){
        repository.insertBook(book)
    }
}