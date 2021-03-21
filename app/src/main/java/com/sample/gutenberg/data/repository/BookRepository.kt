package com.sample.gutenberg.data.repository

import com.sample.gutenberg.data.db.dao.BookDao
import com.sample.gutenberg.data.db.entity.Book
import com.sample.gutenberg.data.db.entity.Category
import com.sample.gutenberg.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val apiService: ApiService
) {
    fun observeBooks() = flow<List<Book>> {
        val list = ArrayList<Book>()
        list.add(
            Book(
                "1",
                "Book Title - 1",
                "Book Subtitle - 1",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "2",
                "Book Title - 2",
                "Book Subtitle - 2",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "3",
                "Book Title - 3",
                "Book Subtitle - 3",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "4",
                "Book Title - 4",
                "Book Subtitle - 4",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "5",
                "Book Title - 5",
                "Book Subtitle - 5",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "6",
                "Book Title - 6",
                "Book Subtitle - 6",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        list.add(
            Book(
                "7",
                "Book Title - 7",
                "Book Subtitle - 7",
                "https://static.wikia.nocookie.net/harrypotter/images/9/97/Harry_Potter.jpg"
            )
        )
        emit(list)
    }
}