package com.arvo.expensemanager.presentation.book.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.data.local.EntryGroup
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import com.arvo.expensemanager.presentation.book.states.EntryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ViewBookViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(EntryState())
    val state: State<EntryState> = _state

    private var getEntryJob: Job? = null

    init {
        getEntryByGroup()
    }

    private fun getEntryByGroup(){
        getEntryJob?.cancel()
        entryUseCases.getEntriesByGroup()
            .onEach { entryGroup ->
                _state.value = state.value.copy(
                    entryGroup = entryGroup
                )
            }
            .launchIn(viewModelScope)
    }
}