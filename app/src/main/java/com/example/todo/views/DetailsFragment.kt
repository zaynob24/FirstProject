package com.example.todo.views

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.database.model.TasksModel
import com.example.todo.objects.DatePickerBuilding
import it.emperor.animatedcheckbox.AnimatedCheckBox


class DetailsFragment : Fragment() {
    private lateinit var titleTextViewDetail: EditText
    private lateinit var dateTextViewDetail: TextView


    private val taskViewModel: TasksViewModel by activityViewModels()
    private lateinit var selectedTask: TasksModel
    private var selectedDate: String = ""
    private lateinit var dueDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        titleTextViewDetail = view.findViewById(R.id.title_textView_detail)
        dateTextViewDetail = view.findViewById(R.id.date_textView_detail)
        val noteTextViewDetail: EditText = view.findViewById(R.id.note_textView_detail)
        val createdDateTextViewDetail: TextView = view.findViewById(R.id.created_date_textView)
        val isDoneCheckboxDetails: AnimatedCheckBox =
            view.findViewById(R.id.isdone_checkbox_details)
        val calendarIconDetail: ImageView = view.findViewById(R.id.calendar_icon_detail)
        val saveIconDetail: ImageView = view.findViewById(R.id.save_icon_detail)
        val cancelIconDetail: ImageView = view.findViewById(R.id.cancel_icon_detail)
        val deleteButtonDetail: Button = view.findViewById(R.id.delete_button_detail)

        taskViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dueDate = it.dueDate
                createdDateTextViewDetail.text =
                    "Created at ${DatePickerBuilding.formatDateReadable(it.createdDate)}"
                titleTextViewDetail.setText(it.title)

                // change color to red if date pass , or make it black if not pass
                dateTextViewDetail.setTextColor(
                    Color.parseColor(DatePickerBuilding.isDueDatePassColor(dueDate))
                )


                dateTextViewDetail.text = DatePickerBuilding.formatDateReadable(it.dueDate)
                noteTextViewDetail.setText(it.note)
                isDoneCheckboxDetails.setChecked(it.isDone)
                selectedTask = it // to use it later for delete button item
            }

            changeDesign(isDoneCheckboxDetails.isChecked())

        })


        isDoneCheckboxDetails.setOnChangeListener {
                changeDesign(isDoneCheckboxDetails.isChecked())
//                if (!isDoneCheckboxDetails.isChecked()) {
//                // change color to red if date pass , or make it black if not pass
//                dateTextViewDetail.setTextColor(
//                    Color.parseColor(
//                        DatePickerBuilding.isDueDatePassColor(
//                            selectedDate
//                        )
//                    )
//                )
//            }
        }

        // delete task
        deleteButtonDetail.setOnClickListener {
            taskViewModel.deleteTask(selectedTask)
            findNavController().popBackStack()
        }

        // go back without making changes
        cancelIconDetail.setOnClickListener {
            findNavController().popBackStack()
        }

        // date picker showing --> calendar Icon clicked
        calendarIconDetail.setOnClickListener {
            // show date picker dialog
            DatePickerBuilding.datePicker.show(requireActivity().supportFragmentManager, "tag")
        }

        // change date --> ok button on date picker clicked
        DatePickerBuilding.datePicker.addOnPositiveButtonClickListener {

            selectedDate = DatePickerBuilding.formatDate(DatePickerBuilding.datePicker.selection)

                //changeDesign(isDoneCheckboxDetails.isChecked())
                // change color to red if date pass , or make it black if not pass
                dateTextViewDetail.setTextColor(
                    Color.parseColor(
                        DatePickerBuilding.isDueDatePassColor(
                            selectedDate
                        )
                    )
                )


            dateTextViewDetail.text = DatePickerBuilding.formatDateReadable(selectedDate)

        }

        // update changes and close fragment
        saveIconDetail.setOnClickListener {

            selectedTask.title = titleTextViewDetail.text.toString()
            selectedTask.isDone = isDoneCheckboxDetails.isChecked()
            if (selectedDate.isNotEmpty()) {
                selectedTask.dueDate = selectedDate
            }
            selectedTask.note = noteTextViewDetail.text.toString()

            taskViewModel.updateTask(selectedTask)
            findNavController().popBackStack()

        }

    }


    private fun changeDesign(isDoneCheckboxDetails: Boolean) {
        if (isDoneCheckboxDetails) {
            // for done tasks make across line design with no date
            titleTextViewDetail.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            dateTextViewDetail.setTextColor(Color.GRAY)
            titleTextViewDetail.setTextColor(Color.GRAY)
        } else {
            // for done tasks make across line design with no date
            titleTextViewDetail.paintFlags = Paint.ANTI_ALIAS_FLAG
            //dateTextViewDetail.setTextColor(Color.BLACK)
            titleTextViewDetail.setTextColor(Color.BLACK)
            // change color to red if date pass , or make it black if not pass
            dateTextViewDetail.setTextColor(
                Color.parseColor(
                    DatePickerBuilding.isDueDatePassColor(
                        dueDate
                    )
                )
            )
        }
    }
}

