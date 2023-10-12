package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arvo.expensemanager.data.model.local.pageData.BookEntryEntity

@Dao
interface BookEntryModelDao : BaseDao<BookEntryEntity> {

    @Insert
    suspend fun inset(pageDataEntity: BookEntryEntity)

    @Query("SELECT * FROM ${BookEntryEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun getList(): List<BookEntryEntity>

    @Update
    override suspend fun update(pageDataEntity: BookEntryEntity)

    @Query("SELECT * FROM ${BookEntryEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): BookEntryEntity?

    @Query("DELETE FROM ${BookEntryEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${BookEntryEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)
}