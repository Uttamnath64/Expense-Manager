package com.arvo.expensemanager.data.model.local.book

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BookEntity.TABLE_NAME)
data class BookEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String?,
    @ColumnInfo(name = COLUMN_UPDATED) val updated: String?,
    @ColumnInfo(name = COLUMN_CREATED) val created: String,
) {
    companion object {
        const val TABLE_NAME = "book"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "descrnameiption"
        const val COLUMN_UPDATED = "updated"
        const val COLUMN_CREATED = "created"
    }
}