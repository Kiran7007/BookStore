package com.sample.gutenberg.ui.fragment.book

sealed class BookIntent {
    class FetchBooks(val categoryId: String) : BookIntent()
}