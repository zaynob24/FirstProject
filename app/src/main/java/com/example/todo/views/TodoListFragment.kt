package com.example.todo.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.model.TasksModel
import com.example.todo.views.adapter.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoListFragment : Fragment() {

    private lateinit var  spineer : Spinner
    private val modalBottomSheet = ModalBottomSheetFragment()
    private val TAG = "ModalBottomSheet"

    private val tasksItem = mutableListOf<TasksModel>()
    private val tasksItemCompleted = mutableListOf<TasksModel>()

    private val taskViewModel : TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // tasks RecyclerView
        val tasksRecyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        val taskAdapter = TaskAdapter(tasksItem,taskViewModel)
        tasksRecyclerView.adapter = taskAdapter


        // tasks complete RecyclerView
        val tasksCompleteRecyclerView : RecyclerView = view.findViewById(R.id.completedTask_recyclerView)
        val taskCompleteAdapter = TaskAdapter(tasksItemCompleted,taskViewModel)
        tasksCompleteRecyclerView.adapter = taskCompleteAdapter

        // get data changes for tasks RecyclerView
        taskViewModel.tasksItems.observe(viewLifecycleOwner, {
            it?.let {

                tasksItem.clear()
                tasksItem.addAll(it)
                taskAdapter.notifyDataSetChanged()

            }
        })

        // get data changes for tasks complete RecyclerView
        taskViewModel.tasksItemsCompleted.observe(viewLifecycleOwner, {
            it?.let {

                tasksItemCompleted.clear()
                tasksItemCompleted.addAll(it)
                taskCompleteAdapter.notifyDataSetChanged()

            }
        })




        val addFloatingActionButton : FloatingActionButton = view.findViewById(R.id.add_floating_button)
        val categories = resources.getStringArray(R.array.categories)
        spineer = view.findViewById<Spinner>(R.id.spinner)

        //number Of Tasks in the list
        val numberOfTasksTextView :TextView = view.findViewById(R.id.numbers_todo_textview)
        numberOfTasksTextView.text = tasksItem.size.toString()

        if (spineer != null)
        {
            val adapter = ArrayAdapter(activity as Context, android.R.layout.simple_spinner_dropdown_item, categories)
            spineer.adapter = adapter
        }


        addFloatingActionButton.setOnClickListener {
            modalBottomSheet.show(requireActivity().supportFragmentManager, TAG)
        }
    }

}