package com.bignerdranch.android.variant3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    // Объявляем переменные для элементов интерфейса
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализируем элементы по их ID
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogIn = findViewById(R.id.btnLogIn)

        // Инициализируем хранилище SharedPreferences
        val sharedPrefs = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        // Настраиваем обработчик нажатия на кнопку "Log in"
        btnLogIn.setOnClickListener {
            val emailInput = etEmail.text.toString().trim()
            val passwordInput = etPassword.text.toString().trim()

            // Проверка на пустые поля ввода
            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                showAlertDialog("Введите логин и пароль")
                return@setOnClickListener
            }

            // Проверяем первый это вход или повторный
            val isFirstLogin = sharedPrefs.getBoolean("IS_FIRST_LOGIN", true)

            if (isFirstLogin) {
                // ПЕРВЫЙ ВХОД: сохраняем то, что ввел пользователь, и переключаем флаг
                sharedPrefs.edit().apply {
                    putString("SAVED_LOGIN", emailInput)
                    putString("SAVED_PASSWORD", passwordInput)
                    putBoolean("IS_FIRST_LOGIN", false)
                    apply()
                }
                navigateToChats()
            } else {
                // достаем сохраненные данные
                val savedLogin = sharedPrefs.getString("SAVED_LOGIN", "")
                val savedPassword = sharedPrefs.getString("SAVED_PASSWORD", "")

                // Проверяем совпадение с сохраненными данными
                if (emailInput == savedLogin && passwordInput == savedPassword &&
                    emailInput == "ects" && passwordInput == "ects2025") {
                    navigateToChats()
                } else {
                    showAlertDialog("Неверный логин или пароль")
                }
            }
        }
    }

    // Функция вызова всплывающего окна AlertDialog
    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
            .show()
    }

    // Функция перехода на второй экран "Chats"
    private fun navigateToChats() {
        val intent = Intent(this, ChatsActivity::class.java)
        startActivity(intent)
        finish() // Закрываем экран логина, чтобы нельзя было вернуться назад по кнопке "Back"
    }
}

