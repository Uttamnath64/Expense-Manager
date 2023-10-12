package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arvo.expensemanager.data.model.local.page.PageEntity
import com.arvo.expensemanager.data.model.local.pageData.PageDataEntity

@Dao
interface PageDataModelDao : BaseDao<PageDataEntity> {

    @Insert
    suspend fun inset(pageDataEntity: PageDataEntity)

    @Query("SELECT * FROM ${PageDataEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun getList(): List<PageDataEntity>

    @Update
    override suspend fun update(pageDataEntity: PageDataEntity)

    @Query("SELECT * FROM ${PageDataEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): PageDataEntity?

    @Query("DELETE FROM ${PageDataEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${PageDataEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)
}