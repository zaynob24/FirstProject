package com.example.todo.database.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.todo.database.model.CategoryModel
import com.example.todo.database.model.TasksModel

data class CategoryModelWithTasksModel (

    @Embedded val tasks: TasksModel,
    @Relation(

        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val categoryModel: List<CategoryModel>
        )