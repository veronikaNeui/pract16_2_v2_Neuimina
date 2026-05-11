package com.example.pract15_1_neuimina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Onboarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }
    fun login(view: View) {
        val intent = Intent (this, Login::class.java)
        startActivity(intent)
    }
}