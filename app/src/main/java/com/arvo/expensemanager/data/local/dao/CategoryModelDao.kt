package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arvo.expensemanager.data.model.local.category.CategoryEntity


@Dao
interface CategoryModelDao : BaseDao<CategoryEntity> {

    @Insert
    suspend fun inset(categoryEntity: CategoryEntity)

    @Update
    override suspend fun update(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun getList(): List<CategoryEntity>

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): CategoryEntity?

    @Query("DELETE FROM ${CategoryEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${CategoryEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)
}