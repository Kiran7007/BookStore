package com.sample.gutenberg.data.repository

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
        list.add(Category("1", "Fiction"))
        list.add(Category("2", "Drama"))
        list.add(Category("3", "Humor"))
        list.add(Category("4", "Politics"))
        list.add(Category("5", "Philosophy"))
        list.add(Category("6", "History"))
        list.add(Category("7", "Adventure"))
        emit(list)
    }
}