package com.arvo.expensemanager.domain.usecase

import com.arvo.expensemanager.domain.usecase.entry.DeleteEntry
import com.arvo.expensemanager.domain.usecase.entry.GetAnalysis
import com.arvo.expensemanager.domain.usecase.entry.GetEntries
import com.arvo.expensemanager.domain.usecase.entry.GetEntriesByGroup
import com.arvo.expensemanager.domain.usecase.entry.GetEntry
import com.arvo.expensemanager.domain.usecase.entry.InsertEntry

data class EntryUseCases(
    val getEntries: GetEntries,
    val getEntriesByGroup: GetEntriesByGroup,
    val getAnalysis: GetAnalysis,
    val getEntry: GetEntry,
    val insertEntry: InsertEntry,
    val deleteEntry: DeleteEntry
)