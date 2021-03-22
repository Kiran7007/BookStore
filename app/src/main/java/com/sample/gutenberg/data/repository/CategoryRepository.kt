package com.sample.gutenberg.data.repository

import com.sample.gutenberg.R
import com.sample.gutenberg.data.db.dao.CategoryDao
import com.sample.gutenberg.data.db.entity.Category
import com.sample.gutenberg.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val apiService: ApiService
) {
    fun observeCategories() = flow<List<Category>> {
        val list = ArrayList<Category>()
        list.add(Category("1", "Fiction", R.drawable.ic_fiction))
        list.add(Category("2", "Drama", R.drawable.ic_drama))
        list.add(Category("3", "Humor", R.drawable.ic_humour))
        list.add(Category("4", "Politics", R.drawable.ic_politics))
        list.add(Category("5", "Philosophy", R.drawable.ic_philosophy))
        list.add(Category("6", "History", R.drawable.ic_history))
        list.add(Category("7", "Adventure", R.drawable.ic_adventure))
        emit(list)
    }
}