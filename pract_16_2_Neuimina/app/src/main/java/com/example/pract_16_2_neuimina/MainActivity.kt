package com.example.pract_16_2_neuimina

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var etUsername: EditText
    private lateinit var btnSave: Button
    private lateinit var btnNext: Button
    private lateinit var cbReset: CheckBox
    private lateinit var tvNote: TextView

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_SAVED = "isSaved"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        etUsername = findViewById(R.id.etUsername)
        btnSave = findViewById(R.id.btnSave)
        btnNext = findViewById(R.id.btnNext)
        cbReset = findViewById(R.id.cbReset)
        tvNote = findViewById(R.id.tvNote)

        checkSavedData()

        setupListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun checkSavedData() {
        val isSaved = sharedPreferences.getBoolean(KEY_IS_SAVED, false)
        val savedUsername = sharedPreferences.getString(KEY_USERNAME, "")

        if (isSaved && !savedUsername.isNullOrEmpty()) {
            etUsername.setText(savedUsername)
            etUsername.isEnabled = false
            btnSave.isEnabled = false
            btnSave.alpha = 0.5f
            tvNote.text = "Имя сохранено: $savedUsername"
        } else {
            tvNote.text = getString(R.string.primechanie)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupListeners() {
        btnSave.setOnClickListener {
            val username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                tvNote.text = "Имя не введено"
            } else {
                val editor = sharedPreferences.edit()
                editor.putString(KEY_USERNAME, username)
                editor.putBoolean(KEY_IS_SAVED, true)
                editor.apply()

                tvNote.text = "Имя сохранено: $username"
                etUsername.isEnabled = false
                btnSave.isEnabled = false
                btnSave.alpha = 0.5f
            }
        }

        btnNext.setOnClickListener {
            val username = sharedPreferences.getString(KEY_USERNAME, "")
            val intent = Intent(this, Second::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        cbReset.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                resetSettings()
            }
        }
    }

    private fun resetSettings() {
        sharedPreferences.edit().clear().apply()
        etUsername.text.clear()
        etUsername.isEnabled = true
        btnSave.isEnabled = true
        btnSave.alpha = 1f
        tvNote.text = getString(R.string.primechanie)
        cbReset.isChecked = false
    }
}
