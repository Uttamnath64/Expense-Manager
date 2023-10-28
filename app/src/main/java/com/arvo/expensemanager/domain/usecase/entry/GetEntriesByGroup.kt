package com.arvo.expensemanager.domain.usecase.entry

import android.annotation.SuppressLint
import android.util.Log
import com.arvo.expensemanager.data.local.EntryGroup
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map

class GetEntriesByGroup(
    private val repository: EntryRepository
) {
    operator fun invoke(): Flow<List<EntryGroup>> {
        return flow {
            try {
                val entries = repository.getEntries()
                val entryGroups = mutableListOf<EntryGroup>()

                entries.collect { entryList ->
                    val groupedEntries = entryList.groupBy { it.timestamp }
                    var index = 0

                    groupedEntries.map { (datetime, entries) ->
                        index++
                        var total = 0.0
                        entries.map {
                            total = if (it.paymentType) total + it.amount else total - it.amount
                        }
                        val adjustedEntries = entries.map { entry ->
                            Entry(
                                id = entry.id,
                                bookId = entry.bookId,
                                title = entry.title,
                                description = entry.description,
                                amount = entry.amount,
                                paymentType = entry.paymentType,
                                paymentMethod = entry.paymentMethod,
                                timestamp = entry.timestamp
                            )
                        }
                        Log.i("uttam1", adjustedEntries.toString())

                        entryGroups.add(
                            EntryGroup(
                                id = index,
                                datetime = datetime,
                                total = total,
                                data = adjustedEntries
                            )
                        )
                        Log.i("uttam2", entryGroups.toString())
                    }
                    Log.i("uttam3", entryGroups.toString())
                }
                Log.i("uttam4",entryGroups.toString())

                val sortedEntryGroups = entryGroups.sortedByDescending { it.datetime }
                emit(sortedEntryGroups)
            } catch (e: Exception) {
                // Handle and log the error
                Log.e("GetEntriesByGroup", "Error in data retrieval: ${e.message}")
            }
        }
    }
}