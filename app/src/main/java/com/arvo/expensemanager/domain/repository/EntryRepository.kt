package com.arvo.expensemanager.domain.repository

import com.arvo.expensemanager.data.local.Analysis
import com.arvo.expensemanager.data.local.EntryGroup
import com.arvo.expensemanager.domain.model.Entry
import dagger.Provides
import kotlinx.coroutines.flow.Flow


interface EntryRepository {
    fun getEntries(id: Int): Flow<List<Entry>>

    suspend fun getAnalysis(id: Int): Analysis

    suspend fun getEntry(id: Int): Entry?

    suspend fun insertEntry(entry: Entry)

    suspend fun deleteEntry(entry: Entry)
}