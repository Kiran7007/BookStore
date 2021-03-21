package com.sample.gutenberg.ui.fragment.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.gutenberg.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    val categoryIntent = Channel<CategoryIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<CategoryState>(CategoryState.Idle)
    val state: StateFlow<CategoryState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            categoryIntent.consumeAsFlow().collect {
                when (it) {
                    is CategoryIntent.FetchCategory -> fetchCategory()
                }
            }
        }
    }

    private fun fetchCategory() {
        viewModelScope.launch {
            repository.observeCategories().collect {
                _state.value = CategoryState.Success(it)
            }
        }
    }

    fun launchBookScreen(categoryId: String) {
        _state.value = CategoryState.NavigateToBooksScreen(categoryId)
    }
}