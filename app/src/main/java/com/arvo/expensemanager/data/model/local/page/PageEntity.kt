package com.arvo.expensemanager.data.model.local.page

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PageEntity.TABLE_NAME)
data class PageEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String?,
    @ColumnInfo(name = COLUMN_UPDATED) val updated: String?,
    @ColumnInfo(name = COLUMN_CREATED) val created: String,
) {
    companion object {
        const val TABLE_NAME = "page"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_UPDATED = "updated"
        const val COLUMN_CREATED = "created"
    }
}