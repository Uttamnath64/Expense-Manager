package com.arvo.expensemanager.presentation.book.events

import androidx.compose.ui.focus.FocusState
import com.arvo.expensemanager.domain.model.Entry

sealed class AddEditEntryEvent{
    data class  EnteredTitle(val value: String): AddEditEntryEvent()
    data class  EnteredDescription(val value: String): AddEditEntryEvent()
    data class  EnteredAmount(val value: String): AddEditEntryEvent()
    data class  SelectPaymentType(val value: Int): AddEditEntryEvent()
    object DeleteEntry: AddEditEntryEvent()
    object SaveEntry: AddEditEntryEvent()
}
