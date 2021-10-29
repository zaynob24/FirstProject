package com.example.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.database.model.TasksModel


@Database(entities = [TasksModel::class],version = 1 )
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao():TodoDao

}