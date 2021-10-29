package com.example.todo.repositories

import android.content.Context
import androidx.room.Room
import com.example.todo.database.TodoDatabase
import com.example.todo.database.model.TasksModel
import java.lang.Exception

private const val DATABASE_NAME = "todo-database"

class TodoRepository(context: Context)  {

    private val database:TodoDatabase =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration() // for any edit of database ,he will Migration, marge old version with new one
            .build()


    private val todoDao = database.todoDao()

    fun getTask() = todoDao.getTask()
    suspend fun addTask(tasksModel: TasksModel)= todoDao.addTask(tasksModel)
    suspend fun updateTask(tasksModel: TasksModel)=todoDao.updateTask(tasksModel)
    suspend fun deleteTask(tasksModel: TasksModel)=todoDao.deleteTask(tasksModel)


    //companion object -> object inside class,
    companion object{
        private var instance : TodoRepository?=null

        fun init(context: Context){
            if (instance==null){
                instance = TodoRepository(context)
            }
        }

        fun get(): TodoRepository{
            return instance?: throw Exception("Todo Repository must be initialized")
        }
    }
}