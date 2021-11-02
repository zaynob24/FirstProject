package com.example.todo.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todo.R
import com.example.todo.objects.DatePickerBuilding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


// this class for add Bottom Sheet that appear when click in add floating button
class ModalBottomSheetFragment : BottomSheetDialogFragment() {

    private var selectedDate :String = ""
    private lateinit var currentDate :String
    private val isDONE = false
    private val NOTE = ""
    private var categoryName = "No category"

    private lateinit var todoTaskTitle :String
    private val tasksViewModel : TasksViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Round shape style
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(android.R.color.transparent)
        val saveButton: Button = view.findViewById(R.id.save_button_sheet)
        val calenderIcon: ImageView = view.findViewById(R.id.calenderIcon_imageView)
        val todoTitleEditText: EditText = view.findViewById(R.id.todo_title_editText)
        val fileIcon: ImageView = view.findViewById(R.id.file_imageView)



        // date picker --> ok button clicked
        DatePickerBuilding.datePicker.addOnPositiveButtonClickListener {

            selectedDate = DatePickerBuilding.formatDate(DatePickerBuilding.datePicker.selection)
            Log.d("selected Date", selectedDate)
        }

        calenderIcon.setOnClickListener {
            // show date picker dialog
            DatePickerBuilding.datePicker.show(requireActivity().supportFragmentManager, "tag")
        }

        fileIcon.setOnClickListener { v: View ->
            // show Category Popup menus

            showMenu(v, R.menu.popup_menu)
        }






           //TODO 1: change button color while setEnabled(false)
          //  https://newbedev.com/change-the-color-of-a-disabled-button-in-android
          //  https://stackoverflow.com/questions/33071410/change-the-color-of-a-disabled-button-in-android


        // save button disabled while no text entered
        todoTitleEditText.addTextChangedListener {
            todoTaskTitle = todoTitleEditText.text.toString()

            if (todoTaskTitle.trim({ it <= ' ' }).isEmpty())
            {
                saveButton.setEnabled(false)
            }
            else
            {
                saveButton.setEnabled(true)
            }
        }


        // save Button clicked --> add data to database
        saveButton.setOnClickListener {
            todoTaskTitle = todoTitleEditText.text.toString()
            currentDate = DatePickerBuilding.formatDate(Calendar.getInstance().time)
            tasksViewModel.addTask(todoTaskTitle,NOTE,isDONE,selectedDate,currentDate,categoryName)
            dismiss()  // close Modal Bottom Sheet Fragment
            todoTitleEditText.text.clear()
        }
    }

    //TODO 2: [[ make edit text clear when press out of button sheet ]]


    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext()!!, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            categoryName =menuItem.toString()
            // Respond to menu item click.
            Log.d("menuItem",menuItem.toString())
            true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }
}


