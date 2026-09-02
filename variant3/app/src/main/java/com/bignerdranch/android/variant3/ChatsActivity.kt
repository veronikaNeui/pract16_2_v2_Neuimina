package com.bignerdranch.android.variant3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast


data class SimpleContact(val name: String, val msg: String, val count: Int)

// Структура данных контакта с ID локального ресурса картинки (остается прямо в этом файле)
data class LocalContact(val name: String, val msg: String, val badge: Int, val imageResId: Int)

class ChatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val lvContacts = findViewById<ListView>(R.id.lvContacts)

        // Настройка ListView: полностью убираем встроенные серые полосы-разделители,
        // так как у наших карточек уже есть свои рамки
        lvContacts.divider = null
        lvContacts.dividerHeight = 0

        // Нажатие на стрелочку "Назад" -> Переход обратно на экран авторизации Log in
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрываем экран чатов
        }

        // Связываем имена контактов и сообщения с вашими файлами: one, two, three, four, five
        val contactsList = listOf(
            LocalContact("John Joshua", "Thanks for your service", 1, R.drawable.one),
            LocalContact("Chinonso James", "Alright, I will be waiting", 0, R.drawable.two),
            LocalContact("Raph Ron", "Thanks for your service", 5, R.drawable.three),
            LocalContact("Joy Ezekiel", "Thanks for your service", 0, R.drawable.four),
            LocalContact("Joy Ezekiel", "Thanks for your service", 1, R.drawable.five)
        )

        // Встроенный адаптер без создания сторонних классов
        lvContacts.adapter = object : BaseAdapter() {
            override fun getCount(): Int = contactsList.size
            override fun getItem(position: Int): Any = contactsList[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val rowView = convertView ?: inflater.inflate(R.layout.item_chat, parent, false)

                val tvName = rowView.findViewById<TextView>(R.id.tvName)
                val tvMessage = rowView.findViewById<TextView>(R.id.tvMessage)
                val tvBadge = rowView.findViewById<TextView>(R.id.tvBadge)
                val ivAvatar = rowView.findViewById<ImageView>(R.id.ivAvatar)

                val contact = contactsList[position]

                // Заполняем текстовые поля и устанавливаем аватар из drawable
                tvName.text = contact.name
                tvMessage.text = contact.msg
                ivAvatar.setImageResource(contact.imageResId)

                // Если у контакта счетчик больше 0 — показываем синий кружок, иначе полностью скрываем его
                if (contact.badge > 0) {
                    tvBadge.visibility = View.VISIBLE
                    tvBadge.text = contact.badge.toString()
                } else {
                    tvBadge.visibility = View.GONE
                }

                return rowView
            }
        }

        // Клик по контакту из списка (по заданию выводит всплывающий текст с именем)
        lvContacts.setOnItemClickListener { _, _, position, _ ->
            val selectedName = contactsList[position].name
            Toast.makeText(this, "Выбран контакт: $selectedName", Toast.LENGTH_SHORT).show()
        }
    }
}