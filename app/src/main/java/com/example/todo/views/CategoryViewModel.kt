package com.example.todo.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.model.CategoryModel
import com.example.todo.database.model.relations.CategoryModelWithTasksModel
import com.example.todo.repositories.TodoRepository
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    val todoRepository = TodoRepository.get()

    val categoryItems = todoRepository.getCategory()

    val getCategoryWithTasks :(name:String) -> LiveData<List<CategoryModelWithTasksModel>> = {
        todoRepository.getCategoryWithTasks(it)
    }

    fun addCategory(name:String,color:String){
        viewModelScope.launch {
            todoRepository.addCategory(CategoryModel(name, color))
        }
    }
}

