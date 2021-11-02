package com.example.todo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModel(

    var categoryColor : String,


    @PrimaryKey(autoGenerate = false)
    var categoryName : String


)
