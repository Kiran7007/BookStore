package com.sample.gutenberg.ui.fragment.category

import com.sample.gutenberg.data.db.entity.Category

sealed class CategoryState() {
    object Idle : CategoryState()
    class Loading(val isLoading: Boolean) : CategoryState()
    class Success(val list: List<Category>) : CategoryState()
    class Error(val message: String) : CategoryState()
    class NavigateToBooksScreen(val categoryId: String) : CategoryState()
}