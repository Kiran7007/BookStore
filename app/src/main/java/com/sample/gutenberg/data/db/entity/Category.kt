package com.sample.gutenberg.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.sample.gutenberg.R

@Entity(tableName = "category")
data class Category(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @Ignore
    var resourceId: Int = android.R.drawable.btn_radio
)