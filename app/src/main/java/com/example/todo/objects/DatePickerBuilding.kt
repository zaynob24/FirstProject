package com.example.todo.objects

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.todo.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DatePickerBuilding {

    // To set date
    // build date Picker dialog
    val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.select_date_header)
            .build()


    //  to make date in ("yyyy MM dd") format
    // <T> --> date parameter come in different data type (Long or Date)
    fun <T> formatDate(date: T): String {

        val format = SimpleDateFormat("yyyy MM dd")
        return format.format(date)
    }

    // to make date format readable
    // use this when get date from data base to show it to user
    //The pattern letters means: E -> day name, M -> Month name, d -> day of month number, y -> the year
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateReadable(date: String): String {

        if(date.isNotEmpty()){

            val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy MM dd"))
            return localDate.format(DateTimeFormatter.ofPattern("E, MMM dd, yyyy"))
        }else{
            return ""
        }

    }



    // check if date pass return color
    val isDueDatePassColor: (String) -> String = { date ->
        val currentDate = formatDate(Calendar.getInstance().time)
        if (date <= currentDate) "#EF4A4A"
        else if (date > currentDate) "#1B1A1A"
        else "#1B1A1A"
    }

}