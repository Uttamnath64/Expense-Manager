package com.arvo.expensemanager.presentation.book.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.presentation.book.events.AddEditBookEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditBookViewModel @Inject constructor(
//    private val bookUseCases: ,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    // title
    private val _bookTitle = mutableStateOf("")

//    val uiState = _uiState.asStateFlow()

    // description
    private val _bookDescription = mutableStateOf("")

    private var currentBookId: Int? = null

    fun onEvent(event: AddEditBookEvent){
    when(event){
        is AddEditBookEvent.SaveBook -> {
            _bookTitle.value = event.title
            _bookDescription.value = event.description
            viewModelScope.launch {
//                try {
//                    bookUseCases.addBookUseCase(
//                        Book(
//
//                        )
//                    )
//                } catch (e: Exception){
//                    e.printStackTrace()
//                }
            }
        }
        is AddEditBookEvent.DeleteBook -> {

        }
    }
    }

}