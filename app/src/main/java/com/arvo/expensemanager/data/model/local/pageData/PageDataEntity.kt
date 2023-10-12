package com.arvo.expensemanager.data.model.local.pageData

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arvo.expensemanager.data.model.local.page.PageEntity

@Entity(tableName = PageDataEntity.TABLE_NAME)
data class PageDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String?,
    @ColumnInfo(name = COLUMN_AMOUNT) val amount: Float,
    @ColumnInfo(name = COLUMN_DATA_TYPE) val dataType: Boolean,
    @Embedded(prefix = COLUMN_CATEGORY_ID) val categoryId: Int?,
    @Embedded(prefix = COLUMN_PAYMENT_TYPE) val paymentType: String?,
    @ColumnInfo(name = COLUMN_CREATED) val created: String,
){
    companion object {
        const val TABLE_NAME = "character_favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_DATA_TYPE = "data_type"
        const val COLUMN_CATEGORY_ID = "category_id"
        const val COLUMN_PAYMENT_TYPE = "payment_type"
        const val COLUMN_CREATED = "created"
    }
}
