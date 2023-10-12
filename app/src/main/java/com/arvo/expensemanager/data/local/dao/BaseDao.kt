package com.arvo.expensemanager.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: T)

    @Delete
    suspend fun delete(data: T)
}