package com.arvo.expensemanager.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.usecase.BookUseCases
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import com.arvo.expensemanager.presentation.book.events.AddEditBookEvent
import com.arvo.expensemanager.presentation.book.viewModels.AddEditBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookUseCases: BookUseCases,
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    // event flow
    private val _eventFLow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFLow.asSharedFlow()


    private var getBookJob: Job? = null

    init {
        getBooks()
    }

    private fun getBooks(){
        getBookJob?.cancel()
        getBookJob =  bookUseCases.getBooks()
            .onEach { homeBook ->
                _state.value = state.value.copy(
                    homeBooks = homeBook
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteBook -> {
                viewModelScope.launch {
                    try {
                        bookUseCases.deleteBook(event.book)
                        _eventFLow.emit(UiEvent.DeleteBook)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object DeleteBook : UiEvent()
    }
}