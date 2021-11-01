package com.example.todo.views.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.opengl.Visibility
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.model.TasksModel
import com.example.todo.objects.DatePickerBuilding
import com.example.todo.views.TasksViewModel
import it.emperor.animatedcheckbox.AnimatedCheckBox
import java.util.*

class TaskAdapter (val tasks:List<TasksModel>, val viewModel: TasksViewModel)
    :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.titleTextView.text = task.title
        holder.isDoneCheckbox.setChecked(task.isDone)
        // Resource: https://android-arsenal.com/details/1/7205


        // change color to red if date pass , or make it black if not pass
        holder.dueDateTextView.setTextColor(
            Color.parseColor(
                DatePickerBuilding.isDueDatePassColor(
                    task.dueDate
                )
            )
        )

        holder.dueDateTextView.text = DatePickerBuilding.formatDateReadable(task.dueDate)

        // to open item Details Fragment
        holder.itemView.setOnClickListener {
            // to store the item selected --> use postValue(item) because it is live data
            viewModel.selectedItemMutableLiveData.postValue(task)
            it.findNavController().navigate(R.id.action_todoListFragment_to_detailsFragment)
        }

        // update database with DoneCheckbox value
        holder.isDoneCheckbox.setOnChangeListener {
            task.isDone = holder.isDoneCheckbox.isChecked()
            viewModel.updateTask(task)
        }


        // for done tasks make across line design with no date
        if (task.isDone) {
            holder.titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.dueDateTextView.visibility = View.GONE
            holder.titleTextView.setTextColor(Color.GRAY)
        }

        if (task.dueDate.isEmpty()){
            holder.dueDateTextView.visibility = View.GONE
        }
    }
    override fun getItemCount(): Int {
        return tasks.size
    }
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleTextView: TextView = view.findViewById(R.id.title_textView)
        val isDoneCheckbox: AnimatedCheckBox = view.findViewById(R.id.isdone_checkbox)
        val dueDateTextView: TextView = view.findViewById(R.id.dueDate_textView)

    }

}
