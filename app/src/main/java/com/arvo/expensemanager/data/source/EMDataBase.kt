package com.arvo.expensemanager.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry

@Database(
    entities = [Book::class,Entry::class],
    version = 1
)
abstract class EMDataBase: RoomDatabase() {

    abstract val bookDao: BookDao
    abstract val entryDao: EntryDao

    companion object{
        const val DATABASE_NAME = "expense_manager"
    }

}