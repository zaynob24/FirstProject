package com.example.todo.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.model.TasksModel
import com.example.todo.repositories.TodoRepository
import kotlinx.coroutines.launch

open class TasksViewModel: ViewModel() {

     val todoRepository = TodoRepository.get()

    val tasksItems = todoRepository.getTask()
     val tasksItemsCompleted = todoRepository.getCompletedTask()

    var selectedItemMutableLiveData = MutableLiveData<TasksModel>()

    fun addTask(title:String,note:String,isDone:Boolean,dueDate:String,createdDate:String){
        viewModelScope.launch {
            todoRepository.addTask(TasksModel(title, note, isDone,dueDate,createdDate))
        }
    }


    fun updateTask(tasksModel: TasksModel){
        viewModelScope.launch {
            todoRepository.updateTask(tasksModel)
        }
    }

    fun deleteTask(tasksModel: TasksModel){
        viewModelScope.launch {
            todoRepository.deleteTask(tasksModel)
        }
    }

}