package com.arvo.expensemanager.presentation.book.viewModels


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.model.Entry
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import com.arvo.expensemanager.presentation.book.events.AddEditEntryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditEntryViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // screen type
    private val _screenType = mutableIntStateOf(0)
    val screenType: State<Int> = _screenType

    // title
    private val _entryTitle = mutableStateOf("")
    val entryTitle: State<String> = _entryTitle

    // description
    private val _entryDescription = mutableStateOf("")
    val entryDescription: State<String> = _entryDescription

    // amount
    private val _entryAmount = mutableStateOf("")
    val entryAmount: State<String> = _entryAmount

    // amount
    private val _entryPaymentMethod = mutableIntStateOf(0)
    val entryPaymentMethod: State<Int> = _entryPaymentMethod

    // event flow
    private val _eventFLow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFLow.asSharedFlow()

    // current id
    private var currentId: Int? = null
    private var bookId: Int? = null
    private var paymentType: Boolean = false
    private var entryData: Entry? = null

    init {
            bookId = savedStateHandle["bookId"]
            _screenType.intValue = savedStateHandle["screenType"] ?: 0

            paymentType = (screenType.value == 0)

        try {
            savedStateHandle.get<Int>("entryId")?.let { entryId ->
                if (entryId != -1) {
                    viewModelScope.launch {
                        entryUseCases.getEntry(entryId)?.also { entry ->
                            entryData = entry
                            currentId = entry.id
                            _entryTitle.value = entry.title
                            _entryDescription.value = entry.description
                            _entryPaymentMethod.value = entry.paymentMethod
                            paymentType = entry.paymentType
                            _entryAmount.value = entry.amount.toString()
                        }
                    }
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun onEvent(event: AddEditEntryEvent){
        when(event){
            is AddEditEntryEvent.EnteredTitle -> {
                _entryTitle.value = event.value
            }
            is AddEditEntryEvent.EnteredDescription -> {
                _entryDescription.value = event.value
            }

            is AddEditEntryEvent.EnteredAmount -> {
                _entryAmount.value = event.value
            }

            is AddEditEntryEvent.SelectPaymentType -> {
                _entryPaymentMethod.intValue = event.value
            }

            is AddEditEntryEvent.SaveEntry -> {
                viewModelScope.launch {
                    try {
                        entryUseCases.insertEntry(
                            Entry(
                                id = currentId,
                                bookId = bookId ?: 0,
                                title = entryTitle.value,
                                description = entryDescription.value,
                                amount = entryAmount.value.toDouble(),
                                paymentType = paymentType,
                                paymentMethod = _entryPaymentMethod.value,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFLow.emit(UiEvent.SaveEntry)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
            is AddEditEntryEvent.DeleteEntry -> {
                viewModelScope.launch {
                    try {
                        if(entryData != null){
                            entryUseCases.deleteEntry(entryData!!)
                            _eventFLow.emit(UiEvent.DeleteEntry)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            else -> {}
        }
    }

    sealed class UiEvent {
        object SaveEntry : UiEvent()
        object DeleteEntry : UiEvent()
    }
}