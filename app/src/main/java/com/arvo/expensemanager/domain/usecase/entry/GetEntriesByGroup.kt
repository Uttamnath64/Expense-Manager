package com.arvo.expensemanager.domain.usecase.entry

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.arvo.expensemanager.data.local.EntryGroup
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetEntriesByGroup(
    private val repository: EntryRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(id: Int): Flow<List<EntryGroup>> {
        return repository.getEntries(id)
            .map { entries ->
                entries.groupBy { LocalDate.ofEpochDay(it.timestamp / 86400000) }
            }
            .map { groupedEntries ->
                val entryGroups = mutableListOf<EntryGroup>()
                var runningTotal = 0.0

                for ((date, entries) in groupedEntries) {
                    runningTotal += entries.sumByDouble { if (it.paymentType) it.amount else -it.amount }
                    val adjustedEntries = entries.sortedByDescending { it.timestamp }.mapIndexed { i,entry ->
                        if(i < 4) {
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
                        }else{
                            null
                        }
                    }.mapNotNull { it }

                    entryGroups.add(
                        EntryGroup(
                            id = entryGroups.size + 1,
                            datetime = date.toEpochDay() * 86400000, // Convert back to milliseconds
                            total = runningTotal,
                            data = adjustedEntries
                        )
                    )
                }

                entryGroups.sortedByDescending { it.datetime }
            }
            .catch { e ->
                // Handle and log the error
                Log.e("GetEntriesByGroup", "Error in data retrieval: ${e.message}")
            }
    }
}