package com.arvo.expensemanager.presentation.book.events

import androidx.compose.ui.focus.FocusState

sealed class AddEditEntryEvent{
    data class  EnteredTitle(val value: String): AddEditEntryEvent()
    data class  EnteredDescription(val value: String): AddEditEntryEvent()
    data class  EnteredAmount(val value: String): AddEditEntryEvent()
    data class  SelectPaymentType(val value: Int): AddEditEntryEvent()
    object SaveEntry: AddEditEntryEvent()
}
