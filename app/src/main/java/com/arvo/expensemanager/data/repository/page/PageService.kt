package com.arvo.expensemanager.data.repository.page

import com.arvo.expensemanager.data.local.dao.CategoryModelDao
import com.arvo.expensemanager.data.local.dao.PageModelDao
import com.arvo.expensemanager.data.model.local.category.CategoryEntity
import com.arvo.expensemanager.data.model.local.page.PageEntity

class PageService
constructor(
    private val dao: PageModelDao
){
    suspend fun insert(pageEntity: PageEntity) = dao.insert(pageEntity)

//    suspend fun update(pageEntity: PageEntity) = dao.update(pageEntity)

    suspend fun getList() = dao.getList()

    suspend fun getByID(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteById(id: Int) = dao.deleteById(id)
}