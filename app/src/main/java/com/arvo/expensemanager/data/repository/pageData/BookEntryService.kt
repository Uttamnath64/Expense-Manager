package com.arvo.expensemanager.data.repository.pageData

import com.arvo.expensemanager.data.local.dao.BookEntryModelDao
import com.arvo.expensemanager.data.model.local.pageData.BookEntryEntity

class BookEntryService
constructor(
    private val dao: BookEntryModelDao
){
    suspend fun insert(pageDataEntity: BookEntryEntity) = dao.insert(pageDataEntity)

//    suspend fun update(pageDataEntity: PageDataEntity) = dao.update(pageDataEntity)

    suspend fun getList() = dao.getList()

    suspend fun getByID(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteById(id: Int) = dao.deleteById(id)
}