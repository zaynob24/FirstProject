package com.example.todo.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


// this class for add Bottom Sheet that appear when click in add floating button
class ModalBottomSheetFragment : BottomSheetDialogFragment() {

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

        // To set date
        // build date Picker dialog
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_date_header)
                .build()

        // date picker --> ok button clicked
        datePicker.addOnPositiveButtonClickListener {

            val selectedDate = formatDate(datePicker.selection)
            Log.d("selected Date", selectedDate)

        }

        calenderIcon.setOnClickListener {
            // show date picker dialog
            datePicker.show(requireActivity().supportFragmentManager, "tag")
        }

    }

    //  to make date in ("yyyy MM dd") format
    // <T> --> date parameter come in different data type (Long or Date)
    private fun <T> formatDate(date: T): String {
        val format = SimpleDateFormat("yyyy MM dd")
        return format.format(date)
    }

    //TODO
    // to make date format readable
    // use this when get date from data base to show it to user
    //The pattern letters means: E -> day name, M -> Month name, d -> day of month number, y -> the year

//    val date = SimpleDateFormat("yyyy MM dd").parse(selectedDate)
//    val format = SimpleDateFormat("E, MMM dd, yyyy")
//    val dateFormatted = format2.format(date)

    //TODO
    // to make date format readable
    // use this to see if due day pass

//            val currentDate = formatDate(Calendar.getInstance().time)
//            if (selectedDate==currentDate){
//                Log.d("addOnPositiveButton","To day")
//            }else if (selectedDate<currentDate){
//                Log.d("addOnPositiveButton","pass day")
//            }

}