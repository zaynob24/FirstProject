package com.example.todo.views.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.model.TasksModel
import com.example.todo.objects.DatePickerBuilding
import com.example.todo.views.TasksViewModel
import it.emperor.animatedcheckbox.AnimatedCheckBox

class TaskAdapter (val tasks:List<TasksModel>, val viewModel: TasksViewModel)
    :RecyclerView.Adapter<TaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.titleTextView.text = task.title
        holder.isDoneCheckbox.setChecked(task.isDone)   // Resource: https://android-arsenal.com/details/1/7205
        holder.dueDateTextView.text = DatePickerBuilding.formatDateReadable(task.dueDate)

        // to open item Details Fragment
        holder.itemView.setOnClickListener {
            // to store the item selected --> use postValue(item) because it is live data
            viewModel.selectedItemMutableLiveData.postValue(task)
            it.findNavController().navigate(R.id.action_todoListFragment_to_detailsFragment)
        }

        // update database with DoneCheckbox value
        holder.isDoneCheckbox.setOnChangeListener{
            task.isDone = holder.isDoneCheckbox.isChecked()
            viewModel.updateTask(task)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){

    val titleTextView : TextView = view.findViewById(R.id.title_textView)
    val isDoneCheckbox : AnimatedCheckBox = view.findViewById(R.id.isdone_checkbox)
    val dueDateTextView : TextView = view.findViewById(R.id.dueDate_textView)

}