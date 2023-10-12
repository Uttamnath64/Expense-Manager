package com.arvo.expensemanager.data.repository.book

import com.arvo.expensemanager.data.local.dao.BookModelDao
import com.arvo.expensemanager.data.model.local.book.BookEntity

class BookService
constructor(
    private val dao: BookModelDao
){
    suspend fun insert(pageEntity: BookEntity) = dao.insert(pageEntity)

//    suspend fun update(pageEntity: PageEntity) = dao.update(pageEntity)

    suspend fun getList() = dao.getList()

    suspend fun getByID(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteById(id: Int) = dao.deleteById(id)
}