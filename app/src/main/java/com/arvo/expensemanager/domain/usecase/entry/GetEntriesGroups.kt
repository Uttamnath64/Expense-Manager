package com.arvo.expensemanager.domain.usecase.entry

import android.annotation.SuppressLint
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.utils.OrderType
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date

class GetEntriesGroups(
    private val repository: EntryRepository,
) {
    @SuppressLint("SimpleDateFormat")
    operator fun invoke(
        orderType: OrderType = OrderType.Descending
    ): Any {

        val entryGroups = mutableListOf<EntryGroup>()
        var currentGroup: EntryGroup

        return repository.getEntries().map { entries ->
            val sortedEntries = when(orderType){
                is OrderType.Ascending -> entries.sortedBy { it.timestamp }
                is OrderType.Descending -> entries.sortedByDescending { it.timestamp }
            }
//
//            for (entry in sortedEntries){
//
//                val formattedDate = SimpleDateFormat("MMM dd, yyyy").format(Date(entry.timestamp))
//
//                if(currentGroup == null || currentGroup.date != formattedDate){
//                    currentGroup = EntryGroup(formattedDate, 0.0, mutableListOf())
//                    entryGroups.add(currentGroup)
//                }
//                currentGroup.entries.add(
//                    EntryDetail(entry.title, entry.amount, currentGroup.total + entry.amount)
//                )
//
//                // Update the total for the current group
//                currentGroup.total += entry.amount
//            }
        }


//        return when(orderType){
//            OrderType.Ascending -> entryGroups.reverse()
//            OrderType.Descending -> entryGroups
//        }
    }
}
data class Entry(val date: Long, val name: String, val amount: Double)
data class EntryDetail(val name: String, val amount: Double, val total: Double)
data class EntryGroup(val date: String, var total: Double, val entries: MutableList<EntryDetail>)




//val entryGroups = mutableListOf<EntryGroup>()
//var currentGroup: EntryGroup? = null
//val entries = repository.getEntries()
//
//val sortedEntries = when(orderType){
//    is OrderType.Ascending -> entries.sortedBy { it.timestamp }
//    is OrderType.Descending -> entries.sortedByDescending { it.timestamp }
//}
//
//for (entry in sortedEntries){
//
//    val formattedDate = SimpleDateFormat("MMM dd, yyyy").format(Date(entry.timestamp))
//
//    if(currentGroup == null || currentGroup.date != formattedDate){
//        currentGroup = EntryGroup(formattedDate, 0.0, mutableListOf())
//        entryGroups.add(currentGroup)
//    }
//    currentGroup.entries.add(
//        EntryDetail(entry.title, entry.amount, currentGroup.total + entry.amount)
//    )
//
//    // Update the total for the current group
//    currentGroup.total += entry.amount
//}
//
//
//return when(orderType){
//    OrderType.Ascending -> entryGroups.reverse()
//    OrderType.Descending -> entryGroups
//}