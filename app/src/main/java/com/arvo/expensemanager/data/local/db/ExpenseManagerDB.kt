package com.arvo.expensemanager.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arvo.expensemanager.data.local.dao.CategoryModelDao
import com.arvo.expensemanager.data.local.dao.PageDataModelDao
import com.arvo.expensemanager.data.local.dao.PageModelDao
import com.arvo.expensemanager.data.model.local.category.CategoryEntity
import com.arvo.expensemanager.data.model.local.page.PageEntity
import com.arvo.expensemanager.data.model.local.pageData.PageDataEntity

@Database(
    entities = [PageEntity::class, PageDataEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class ExpenseManagerDB : RoomDatabase() {
    abstract fun PageModelDao(): PageModelDao
    abstract fun PageDataModelDao(): PageDataModelDao
    abstract fun CategoryModelDao(): CategoryModelDao

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: ExpenseManagerDB? = null

        fun getInstance(context: Context): ExpenseManagerDB {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseManagerDB::class.java,
                        "expense_manager_db"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}