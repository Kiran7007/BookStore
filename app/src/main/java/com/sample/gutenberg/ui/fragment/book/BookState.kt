package com.sample.gutenberg.ui.fragment.book

import com.sample.gutenberg.data.db.entity.Book

sealed class BookState() {
    object Idle : BookState()
    class Loading(val isLoading: Boolean) : BookState()
    class Success(val list: List<Book>) : BookState()
    class Error(val message: String) : BookState()
}