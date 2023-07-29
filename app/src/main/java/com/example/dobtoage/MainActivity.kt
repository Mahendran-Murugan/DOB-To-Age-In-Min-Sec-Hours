package com.example.dobtoage

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var txtViewDOB:TextView? = null
    private var txtViewHrs:TextView? = null
    private var txtViewMin:TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateButton:Button = findViewById(R.id.date_picker)
        txtViewDOB = findViewById(R.id.tvDob)
        txtViewHrs = findViewById(R.id.tvhrs)
        txtViewMin = findViewById(R.id.tvmin)
        dateButton.setOnClickListener{
            datePickerClick()
        }
    }
    private fun datePickerClick(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePic=DatePickerDialog(this,
            {_,selYear,selMonth,selDay ->
                Toast.makeText(this,"DOB is Selected",Toast.LENGTH_LONG).show()

                val selectedYear = "$selDay/${selMonth+1}/$selYear"
                txtViewDOB?.text = selectedYear

                val simDatFor = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selDOB = simDatFor.parse(selectedYear)
                selDOB?.let {
                    val selDOBInMin = selDOB.time / 60000
                    val curDate = simDatFor.parse(simDatFor.format(System.currentTimeMillis()))
                    curDate?.let{
                        val curDateInMin = curDate.time /60000
                        val diffMin = curDateInMin - selDOBInMin
                        txtViewHrs?.text = (diffMin/60).toString()
                        txtViewMin?.text = diffMin.toString()
                    }
                   }
            },
        year,
        month,
        day)
        datePic.datePicker.maxDate = System.currentTimeMillis()
        datePic.show()
    }
}