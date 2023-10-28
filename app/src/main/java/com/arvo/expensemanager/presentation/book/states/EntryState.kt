package com.arvo.expensemanager.presentation.book.states

import com.arvo.expensemanager.data.local.EntryGroup

data class EntryState(
    val entryGroup: List<EntryGroup> = emptyList()
)
