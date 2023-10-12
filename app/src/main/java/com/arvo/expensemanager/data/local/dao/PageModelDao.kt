package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arvo.expensemanager.data.model.local.page.PageEntity

@Dao
interface PageModelDao : BaseDao<PageEntity> {

    @Insert
    suspend fun inset(pageEntity: PageEntity)

    @Query("SELECT * FROM ${PageEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun getList(): List<PageEntity>

    @Update
    override suspend fun update(pageEntity: PageEntity)

    @Query("SELECT * FROM ${PageEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): PageEntity?

    @Query("DELETE FROM ${PageEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${PageEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)
}