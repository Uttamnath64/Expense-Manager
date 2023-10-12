package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arvo.expensemanager.data.model.local.book.BookEntity

@Dao
interface BookModelDao : BaseDao<BookEntity> {

    @Insert
    suspend fun inset(pageEntity: BookEntity)

    @Query("SELECT * FROM ${BookEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun getList(): List<BookEntity>

    @Update
    override suspend fun update(pageEntity: BookEntity)

    @Query("SELECT * FROM ${BookEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): BookEntity?

    @Query("DELETE FROM ${BookEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${BookEntity.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)
}