package com.arvo.expensemanager.domain.usecase.entry

import com.arvo.expensemanager.data.local.Analysis
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.BookRepository
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAnalysis(
    private val repository: EntryRepository
) {
    suspend operator fun invoke(id: Int): Analysis{
        return repository.getAnalysis(id)
    }
}