package com.arvo.expensemanager.domain.repository


import com.arvo.expensemanager.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<List<Book>>

    suspend fun getBook(id: Int): Book?

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(book: Book)
}