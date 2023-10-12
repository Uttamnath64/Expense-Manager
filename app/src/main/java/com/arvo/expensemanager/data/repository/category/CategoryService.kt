package com.arvo.expensemanager.data.repository.category

import com.arvo.expensemanager.data.local.dao.CategoryModelDao
import com.arvo.expensemanager.data.model.local.category.CategoryEntity

class CategoryService
constructor(
    private val dao: CategoryModelDao
){
    suspend fun insert(categoryEntity: CategoryEntity) = dao.insert(categoryEntity)

//    suspend fun update(categoryEntity: CategoryEntity) = dao.update(categoryEntity)

    suspend fun getList() = dao.getList()

    suspend fun getByID(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteById(id: Int) = dao.deleteById(id)
}