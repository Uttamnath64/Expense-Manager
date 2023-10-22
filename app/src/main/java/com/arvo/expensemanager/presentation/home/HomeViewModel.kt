package com.arvo.expensemanager.presentation.home

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
//    private val booksUseCases: ViewBooksUseCases
): ViewModel() {
    init {
        getBooks()
    }

    private fun getBooks(){

    }
}