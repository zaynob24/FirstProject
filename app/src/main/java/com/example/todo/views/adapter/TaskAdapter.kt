package com.example.todo.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.model.TasksModel
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

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.titleTextView.text = task.title
        holder.isDoneCheckbox.setChecked(task.isDone)   // Resource: https://android-arsenal.com/details/1/7205
        holder.dueDateTextView.text = task.dueDate

//        /** ME >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//        holder.itemView.setOnClickListener {
//            // to store the item selected
//            // we use postValue(item) because it is live data
//            viewModel.selectedItemMutableLiveData.postValue(item)
//            it.findNavController().navigate(R.id.action_inventoryListFragment_to_itemDetailesFragment)
//        }
//
//        holder.inStock.setOnClickListener{
//            item.inStock = holder.inStock.isChecked
//            viewModel.updateItem(item)
//        }
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