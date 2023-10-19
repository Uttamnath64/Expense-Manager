package com.arvo.expensemanager.data.model.local.bookEntry

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BookEntryEntity.TABLE_NAME)
data class BookEntryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_title) val title: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = COLUMN_AMOUNT) val amount: Float,
    @ColumnInfo(name = COLUMN_TYPE) val type: Boolean,
    @Embedded(prefix = COLUMN_BOOK_ID) val bookId: Int?,
    @Embedded(prefix = COLUMN_PAYMENT_TYPE) val paymentType: Int?,
    @ColumnInfo(name = COLUMN_DATE) val date: String,
){
    companion object {
        const val TABLE_NAME = "book_entry"
        const val COLUMN_ID = "id"
        const val COLUMN_title = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_TYPE = "type"
        const val COLUMN_BOOK_ID = "book_id"
        const val COLUMN_PAYMENT_TYPE = "payment_type"
        const val COLUMN_DATE = "date"
    }
}
