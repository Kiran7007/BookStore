package com.sample.gutenberg.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.gutenberg.data.db.dao.BookDao
import com.sample.gutenberg.data.db.dao.CategoryDao
import com.sample.gutenberg.data.db.entity.Book
import com.sample.gutenberg.data.db.entity.Category
import com.sample.gutenberg.utils.DATABASE_VERSION

@Database(
    version = DATABASE_VERSION,
    entities = [Book::class, Category::class],
    exportSchema = false
)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun bookDao(): BookDao
}