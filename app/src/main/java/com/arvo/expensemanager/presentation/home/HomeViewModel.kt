package com.arvo.expensemanager.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.usecase.BookUseCases
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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
}