package com.effone.datatimedialog



import android.os.Build

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {


    private var time: Long = 0


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        set_date_and_time.setOnClickListener {
            showDataTimePicker()
        }
    }


fun showDataTimePicker(){
    val dialogView: View = View.inflate(this, R.layout.date_time_picker, null)
    val alertDialog = AlertDialog.Builder(this).create()

    dialogView.findViewById<Button>(R.id.date_time_set).setOnClickListener {
        val datePicker = dialogView.findViewById(R.id.date_picker) as DatePicker
        val timePicker = dialogView.findViewById(R.id.time_picker) as TimePicker
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(
            datePicker.year,
            datePicker.month,
            datePicker.dayOfMonth,
            timePicker.hour,
            timePicker.minute
        )
        set_date_and_time.text=fromCaldarToDate(calendar)
        alertDialog.dismiss()
    }

    alertDialog.setView(dialogView)
    alertDialog.show()
}
    fun fromCaldarToDate(calendar: Calendar?): String {
        try {
            val date = calendar?.time
            val sdf1 = SimpleDateFormat("yyyy MMMM dd, hh:mm a", Locale.US)
            return sdf1.format(date)
        } catch (e: ParseException) {
            return e.localizedMessage
        }
    }
}
