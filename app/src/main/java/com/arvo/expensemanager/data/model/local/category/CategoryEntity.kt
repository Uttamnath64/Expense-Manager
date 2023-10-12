package com.arvo.expensemanager.data.model.local.category

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String
) {
    companion object {
        const val TABLE_NAME = "category"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}
