package com.arvo.expensemanager.domain.usecase.entry

import android.annotation.SuppressLint
import com.arvo.expensemanager.data.local.EntryGroup
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetEntries(
    private val repository: EntryRepository
) {
    operator fun invoke(
        orderType: OrderType = OrderType.Descending
    ): Flow<List<Entry>> {
        return repository.getEntries().map { books ->
            when(orderType){
                is OrderType.Ascending -> books.sortedBy { it.timestamp }
                is OrderType.Descending -> books.sortedByDescending { it.timestamp }
                else -> { books}
            }
        }
    }
}