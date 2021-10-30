package com.example.todo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.repositories.TodoRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this is single object for one time to initialize repository .. then we use get function
        TodoRepository.init(this)
    }
}