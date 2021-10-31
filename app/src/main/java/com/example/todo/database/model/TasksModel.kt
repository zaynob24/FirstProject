package com.example.todo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TasksModel(

    var title : String,
    var note : String = "",
    var isDone : Boolean = false,
    var dueDate : String ="",
    val createdDate : String,

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0

)
