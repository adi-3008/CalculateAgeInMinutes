package com.example.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View.VISIBLE
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var txtSelectedDate : TextView? = null
    private var tvAgeInMinute : TextView? = null
    private var tvMinute : TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinutes)
        tvMinute = findViewById(R.id.tvMinute)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_WEEK)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                txtSelectedDate?.text = selectedDate
                txtSelectedDate?.visibility = VISIBLE

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currDate?.let {
                        val currDateInMinutes = currDate.time / 60000
                        val diffInMinutes = currDateInMinutes - selectedDateInMinutes

                        tvAgeInMinute?.text = diffInMinutes.toString()
                        tvAgeInMinute?.visibility = VISIBLE
                        tvMinute?.visibility = VISIBLE
                    }

                }


            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}