package com.arvo.expensemanager.data.repository

import com.arvo.expensemanager.data.source.EntryDao
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.BookRepository
import com.arvo.expensemanager.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

class EntryRepositoryImpl(
    private val dao: EntryDao
): EntryRepository {

    override fun getEntries(): Flow<List<Entry>> {
        return dao.getEntries()
    }

    override suspend fun getEntry(id: Int): Entry? {
        return dao.getEntry(id)
    }

    override suspend fun insertEntry(entry: Entry) {
        dao.insertEntry(entry)
    }

    override suspend fun deleteEntry(entry: Entry) {
        dao.deleteEntry(entry)
    }
}