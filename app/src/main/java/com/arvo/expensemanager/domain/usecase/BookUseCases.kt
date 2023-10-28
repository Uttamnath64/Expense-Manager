package com.arvo.expensemanager.domain.usecase

import com.arvo.expensemanager.domain.usecase.book.DeleteBook
import com.arvo.expensemanager.domain.usecase.book.GetBook
import com.arvo.expensemanager.domain.usecase.book.GetBooks
import com.arvo.expensemanager.domain.usecase.book.InsertBook
import javax.inject.Inject


data class BookUseCases(
    val getBooks: GetBooks,
    val getBook: GetBook,
    val insertBook: InsertBook,
    val deleteBook: DeleteBook
)