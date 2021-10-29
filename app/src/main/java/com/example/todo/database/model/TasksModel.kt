package com.example.todo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TasksModel(

    val title : String,
    val note : String = "",
    val isDone : Boolean = false,
    val dueDate : String ="",
    val createdDate : String,

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0

)
