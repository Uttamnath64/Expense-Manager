package com.arvo.expensemanager.presentation.book.events

sealed class AddEditBookEvent{

    object DeleteBook: AddEditBookEvent()
    data class SaveBook(val title:String, val description: String) : AddEditBookEvent()

}
