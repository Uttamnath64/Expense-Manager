package com.arvo.expensemanager.data.repository.pageData

import com.arvo.expensemanager.data.local.dao.CategoryModelDao
import com.arvo.expensemanager.data.local.dao.PageDataModelDao
import com.arvo.expensemanager.data.local.dao.PageModelDao
import com.arvo.expensemanager.data.model.local.category.CategoryEntity
import com.arvo.expensemanager.data.model.local.pageData.PageDataEntity

class PageDataService
constructor(
    private val dao: PageDataModelDao
){
    suspend fun insert(pageDataEntity: PageDataEntity) = dao.insert(pageDataEntity)

//    suspend fun update(pageDataEntity: PageDataEntity) = dao.update(pageDataEntity)

    suspend fun getList() = dao.getList()

    suspend fun getByID(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteById(id: Int) = dao.deleteById(id)
}