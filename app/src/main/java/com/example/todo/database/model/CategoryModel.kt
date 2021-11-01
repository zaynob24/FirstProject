package com.example.todo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModel(

    var categoryName : String,
    var categoryColor : Int,

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)
