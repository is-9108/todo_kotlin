package com.example.todo_firebase

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)


        val button= findViewById<Button>(R.id.entry)
        val datePicker = findViewById<DatePicker>(R.id.todo_time)
        val todoText = findViewById<TextView>(R.id.todoTextView)

        val add = AddTodo()


        button.setOnClickListener {
            println("-------------------")
            println("tap datePicker")
            println("day:" + datePicker.dayOfMonth)
            println("month:" + datePicker.month + 1)
            println(todoText.text)

            add.addTodo((todoText.text).toString(), (datePicker.month + 1).toString() + '/' + (datePicker.dayOfMonth).toString())
            finish()
        }
    }
}