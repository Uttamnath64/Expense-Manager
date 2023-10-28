package com.arvo.expensemanager.domain.repository


import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<List<HomeBook>>

    suspend fun getBook(id: Int): Book?

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(book: Book)
}