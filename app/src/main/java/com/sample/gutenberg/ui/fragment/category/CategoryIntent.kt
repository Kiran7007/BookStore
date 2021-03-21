package com.sample.gutenberg.ui.fragment.category

sealed class CategoryIntent {
    object FetchCategory : CategoryIntent()
}