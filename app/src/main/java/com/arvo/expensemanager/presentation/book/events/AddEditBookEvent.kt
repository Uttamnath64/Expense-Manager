package com.arvo.expensemanager.presentation.book.events

import androidx.compose.ui.focus.FocusState

sealed class AddEditBookEvent{
    data class  EnteredTitle(val value: String): AddEditBookEvent()
    data class  EnteredDescription(val value: String): AddEditBookEvent()
    object SaveBook: AddEditBookEvent()
}
