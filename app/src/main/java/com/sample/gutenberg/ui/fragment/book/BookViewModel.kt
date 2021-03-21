package com.sample.gutenberg.ui.fragment.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.gutenberg.data.repository.BookRepository
import com.sample.gutenberg.data.repository.CategoryRepository
import com.sample.gutenberg.ui.fragment.category.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    val bookIntent = Channel<BookIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<BookState>(BookState.Idle)
    val state: StateFlow<BookState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            bookIntent.consumeAsFlow().collect {
                when (it) {
                    is BookIntent.FetchBooks -> fetchBooks(it.categoryId)
                }
            }
        }
    }

    private fun fetchBooks(categoryId: String) {
        viewModelScope.launch {
            repository.observeBooks().collect {
                _state.value = BookState.Success(it)
            }
        }
    }
}