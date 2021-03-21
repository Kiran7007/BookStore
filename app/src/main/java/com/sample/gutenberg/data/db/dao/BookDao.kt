package com.sample.gutenberg.data.db.dao

import androidx.room.*
import com.sample.gutenberg.data.db.entity.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Book>)

    @Query("SELECT * FROM book")
    fun fetchAllBooks(): Flow<List<Book>>

    @Update
    fun update(vararg book: Book): Int

    @Delete
    fun delete(vararg book: Book)
}