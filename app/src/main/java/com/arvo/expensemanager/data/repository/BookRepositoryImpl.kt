package com.arvo.expensemanager.data.repository

import com.arvo.expensemanager.data.source.BookDao
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
    private val dao: BookDao
): BookRepository {

    override fun getBooks(): Flow<List<Book>> {
        return dao.getBooks()
    }

    override suspend fun getBook(id: Int): Book? {
        return dao.getBook(id)
    }

    override suspend fun insertBook(book: Book) {
        dao.insertBook(book)
    }

    override suspend fun deleteBook(book: Book) {
        dao.deleteBook(book)
    }
}