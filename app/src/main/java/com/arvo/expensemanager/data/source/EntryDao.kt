package com.arvo.expensemanager.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arvo.expensemanager.data.local.Analysis
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry  WHERE bookId = :id")
    fun getEntries(id: Int): Flow<List<Entry>>

    @Query("SELECT * FROM entry WHERE id = :id")
    suspend fun getEntry(id: Int): Entry?

    @Query(
        """SELECT
                SUM( CASE WHEN paymentType = 1 THEN amount ELSE 0 END ) AS cashOutTotalThisMonth,
                SUM( CASE WHEN paymentType = 0 THEN amount ELSE 0 END ) AS cashInTotalThisMonth,
                SUM( CASE WHEN paymentType = 1 THEN
                    CASE WHEN strftime('%Y-%m', timestamp, 'unixepoch') = strftime('%Y-%m', 'now', '-1 month')
                         THEN amount
                         ELSE 0
                    END
                    ELSE 0
                END ) AS cashOutTotalLastMonth,
                SUM ( CASE WHEN paymentType = 0 THEN
                    CASE WHEN strftime('%Y-%m', timestamp, 'unixepoch') = strftime('%Y-%m', 'now', '-1 month')
                         THEN amount
                         ELSE 0
                    END
                    ELSE 0
                END ) AS cashInTotalLastMonth,
                SUM( CASE WHEN paymentMethod = 1 THEN amount ELSE 0 END ) AS cash,
                SUM( CASE WHEN paymentMethod = 2 THEN amount ELSE 0 END ) AS upi,
                SUM( CASE WHEN paymentMethod = 3 THEN amount ELSE 0 END ) AS bank,
                (SUM(CASE WHEN paymentType = 1 THEN amount ELSE 0 END) + SUM(CASE WHEN paymentType = 0 THEN amount ELSE 0 END)) AS total
        FROM entry
        WHERE bookId = :id
            """
    )
    suspend fun getAnalysis(id: Int): Analysis
//    AND (
//    strftime('%Y-%m', timestamp, 'unixepoch') = strftime('%Y-%m', 'now')
//    OR (strftime('%Y-%m', timestamp, 'unixepoch') = strftime('%Y-%m', 'now', '-1 month'))
//    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(book: Entry)

    @Delete
    suspend fun deleteEntry(book: Entry)
}