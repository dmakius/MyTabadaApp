package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker);

        btnDatePicker.setOnClickListener{
            clickDatePicker();

        }
    }

    fun clickDatePicker(){
        val  myCalendar = Calendar.getInstance()
        val year = myCalendar.getActualMaximum(Calendar.YEAR)
        val month = myCalendar.getActualMaximum(Calendar.MONTH)
        val day = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                Toast.makeText(this,
                    "btnDatePicker Cliked", Toast.LENGTH_LONG
                ).show();
        },
            year,
            month,
            day
            ).show()


    }
}