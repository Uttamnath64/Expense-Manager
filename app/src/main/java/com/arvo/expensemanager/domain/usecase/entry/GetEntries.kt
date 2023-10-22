package com.arvo.expensemanager.domain.usecase.entry

import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEntries(
    private val repository: EntryRepository
) {
    operator fun invoke(
        orderType: OrderType = OrderType.Ascending
    ): Flow<List<Entry>>{
        return repository.getEntries().map { entries ->
            when(orderType){
                is OrderType.Ascending -> entries.sortedBy { it.timestamp }
                is OrderType.Descending -> entries.sortedByDescending { it.timestamp }
            }
        }
    }

}