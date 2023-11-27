package com.arvo.expensemanager.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT *, (SELECT SUM(amount)  FROM entry WHERE id = book.id and paymentType = 0) AS total FROM book")
    fun getBooks(): Flow<List<HomeBook>>
//    (SELECT SUM(amount) FROM entry WHERE id = book.id and paymentType = 1)
    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBook(id: Int): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}