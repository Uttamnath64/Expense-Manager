package com.arvo.expensemanager.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arvo.expensemanager.data.local.dao.CategoryModelDao
import com.arvo.expensemanager.data.local.dao.BookEntryModelDao
import com.arvo.expensemanager.data.local.dao.BookModelDao
import com.arvo.expensemanager.data.model.local.category.CategoryEntity
import com.arvo.expensemanager.data.model.local.book.BookEntity
import com.arvo.expensemanager.data.model.local.pageData.BookEntryEntity

@Database(
    entities = [BookEntity::class, BookEntryEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class ExpenseManagerDB : RoomDatabase() {
    abstract fun PageModelDao(): BookModelDao
    abstract fun PageDataModelDao(): BookEntryModelDao
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