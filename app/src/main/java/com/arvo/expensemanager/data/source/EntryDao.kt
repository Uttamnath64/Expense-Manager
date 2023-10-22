package com.arvo.expensemanager.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM book_entry")
    fun getEntries(): Flow<List<Entry>>

    @Query("SELECT * FROM book_entry WHERE id = :id")
    suspend fun getEntry(id: Int): Entry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(book: Entry)

    @Delete
    suspend fun deleteEntry(book: Entry)
}