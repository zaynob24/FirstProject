package com.example.todo.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addFloatingActionButton : FloatingActionButton = view.findViewById(R.id.add_button)

        addFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_detailsFragment)
        }
    }

}