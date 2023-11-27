package com.arvo.expensemanager.presentation.home

import androidx.compose.ui.focus.FocusState
import com.arvo.expensemanager.domain.model.Book

sealed class HomeEvent{
    data class  DeleteBook(val book: Book): HomeEvent()

}
