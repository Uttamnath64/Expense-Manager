package com.arvo.expensemanager.domain.usecase

import com.arvo.expensemanager.domain.usecase.book.DeleteBook
import com.arvo.expensemanager.domain.usecase.book.GetBook
import com.arvo.expensemanager.domain.usecase.book.GetBooks
import com.arvo.expensemanager.domain.usecase.book.InsertBook
import com.arvo.expensemanager.domain.usecase.entry.DeleteEntry
import com.arvo.expensemanager.domain.usecase.entry.GetEntries
import com.arvo.expensemanager.domain.usecase.entry.GetEntriesGroups
import com.arvo.expensemanager.domain.usecase.entry.GetEntry
import com.arvo.expensemanager.domain.usecase.entry.InsertEntry

data class EntryUseCases(
    val getEntries: GetEntries,
    val getEntriesGroups: GetEntriesGroups,
    val getEntry: GetEntry,
    val insertEntry: InsertEntry,
    val deleteEntry: DeleteEntry
)