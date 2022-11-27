package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
    btnDatePicker.setOnClickListener {
      clickDatePicker()
    }
  }

  fun clickDatePicker() {
    val myCalendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Calendar.getInstance()
    } else {
      TODO("VERSION.SDK_INT < N")
    }
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    DatePickerDialog(this, { view, year, month, day ->
      Toast.makeText(this, "btnDatePicked pressed ${year + 1} + ${month - 1}", Toast.LENGTH_LONG)
        .show()
    }, year, month, day).show()
  }
}