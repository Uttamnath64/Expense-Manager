package com.arvo.expensemanager.presentation.book.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.data.local.Analysis
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(Analysis(
        cashOutTotalThisMonth = 0.0,
        cashInTotalThisMonth = 0.0,
        cashOutTotalLastMonth = 0.0,
        cashInTotalLastMonth = 0.0,
        cash = 0.0,
        upi = 0.0,
        bank = 0.0,
        total = 0.0
    ))
    val state: State<Analysis> = _state

    private var getEntryJob: Job? = null
    private var bookId: Int = 0

    init {
        try {
            bookId = savedStateHandle["bookId"] ?: 0
            getAnalysis()
            Log.i("uttam",state.toString())
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private  fun getAnalysis(){
        getEntryJob?.cancel()
        viewModelScope.launch {
            _state.value = entryUseCases.getAnalysis(bookId)
        }
    }
}