package com.example.pract15_1_neuimina

import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timer=object :CountDownTimer(3000, 1000)
        {
            override fun onTick(millisUntilFinished: Long)
            {

            }

            override fun onFinish() {
                val intent=Intent (this@MainActivity, Onboarding::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()
    }
}