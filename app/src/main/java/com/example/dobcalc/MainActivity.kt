package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

const val MILLISECONDS_MINUTES_RATIO = 60 * 1000;
const val MAX_TIME = 86400000;

class MainActivity : AppCompatActivity() {

  private var tvSelectedDate: TextView? = null
  private var tvDateDifference: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
    tvSelectedDate = findViewById(R.id.tvSelectedDate)
    tvDateDifference = findViewById(R.id.tvDateDifference)
    btnDatePicker.setOnClickListener {
      clickDatePicker()
    }
  }

  private fun clickDatePicker() {
    val myCalendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Calendar.getInstance()
    } else {
      throw Error("SDK Version is old")
    }
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
      val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
      tvSelectedDate?.text = selectedDate
      val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
      val date = dateFormat.parse(selectedDate)
      date?.let {
        val selectedDateInMinutes = date.time / MILLISECONDS_MINUTES_RATIO
        val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
        currentDate?.let {
          val currentDateInMinutes = currentDate.time / MILLISECONDS_MINUTES_RATIO
          val difference = currentDateInMinutes - selectedDateInMinutes
          tvDateDifference?.text = difference.toString()
        }
      }
    }, year, month, day)
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - MAX_TIME
    datePickerDialog.show()
  }
}