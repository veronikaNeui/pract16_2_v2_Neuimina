package com.example.pr14_v5_neuimina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Calendar

class calendar : AppCompatActivity() {
    lateinit var dt:android.icu.util.Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

    }
}