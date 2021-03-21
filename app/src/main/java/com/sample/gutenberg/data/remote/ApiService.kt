package com.sample.gutenberg.data.remote

import com.sample.gutenberg.data.db.entity.Book
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<Book>>
}