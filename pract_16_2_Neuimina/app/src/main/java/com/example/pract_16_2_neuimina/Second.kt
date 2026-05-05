package com.example.pract_16_2_neuimina

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Second : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvDisplayName: TextView
    private lateinit var tvStatus: TextView
    private lateinit var btnClear: Button
    private lateinit var btnBack: Button

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_SAVED = "isSaved"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        tvDisplayName = findViewById(R.id.tvDisplayName)
        tvStatus = findViewById(R.id.tvStatus)
        btnClear = findViewById(R.id.btnClear)
        btnBack = findViewById(R.id.btnBack)
        displayUserInfo()
        setupListeners()
    }

    private fun displayUserInfo() {
        val savedUsername = sharedPreferences.getString(KEY_USERNAME, "")
        val isSaved = sharedPreferences.getBoolean(KEY_IS_SAVED, false)
        val usernameFromIntent = intent.getStringExtra("USERNAME")
        val finalUsername = if (!usernameFromIntent.isNullOrEmpty()) {
            usernameFromIntent
        } else {
            savedUsername
        }

        if (isSaved && !finalUsername.isNullOrEmpty()) {
            tvDisplayName.text = finalUsername
            tvStatus.text = "✓ Статус: Имя сохранено"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else {
            tvDisplayName.text = "Имя не введено"
            tvStatus.text = "✗ Статус: Имя отсутствует"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        btnClear.setOnClickListener {
            tvDisplayName.text = "Данные скрыты"
            tvStatus.text = "Статус: Данные временно скрыты"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_orange_dark))
        }
    }
}
