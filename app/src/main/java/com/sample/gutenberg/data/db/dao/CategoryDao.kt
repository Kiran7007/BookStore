package com.sample.gutenberg.data.db.dao

import androidx.room.*
import com.sample.gutenberg.data.db.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Category>)

    @Query("SELECT * FROM category")
    fun fetchAllCategory(): Flow<List<Category>>

    @Update
    fun update(vararg category: Category): Int

    @Delete
    fun delete(vararg category: Category)
}