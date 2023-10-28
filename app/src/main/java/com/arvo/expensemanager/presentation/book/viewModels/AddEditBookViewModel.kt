package com.arvo.expensemanager.presentation.book.viewModels


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.domain.usecase.BookUseCases
import com.arvo.expensemanager.presentation.book.events.AddEditBookEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditBookViewModel @Inject constructor(
    private val bookUseCases: BookUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // screen type
    private val _screenType = mutableStateOf(0)
    val screenType: State<Int> = _screenType

    // title
    private val _bookTitle = mutableStateOf("")
    val bookTitle: State<String> = _bookTitle

    // description
    private val _bookDescription = mutableStateOf("")
    val bookDescription: State<String> = _bookDescription

    // event flow
    private val _eventFLow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFLow.asSharedFlow()

    // current id
    private var currentId: Int? = null

    init {
        savedStateHandle.get<Int>("bookId")?.let { bookId ->
            if(bookId != -1){
                _screenType.value = 1
                viewModelScope.launch {
                    bookUseCases.getBook(bookId)?.also { book ->
                        currentId = book.id
                        _bookTitle.value = book.title
                        _bookDescription.value = book.description
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditBookEvent){
        when(event){
            is AddEditBookEvent.EnteredTitle -> {
                _bookTitle.value = event.value
            }
            is AddEditBookEvent.EnteredDescription -> {
                _bookDescription.value = event.value
            }

            is AddEditBookEvent.SaveBook -> {
                viewModelScope.launch {
                    try {
                        bookUseCases.insertBook(
                            Book(
                                id = currentId,
                                title = bookTitle.value,
                                description = bookDescription.value,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFLow.emit(UiEvent.SaveBook)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
            else -> {}
        }
    }

    sealed class UiEvent {
        object SaveBook : UiEvent()
    }
}