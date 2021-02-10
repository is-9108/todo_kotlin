package com.example.todo_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.widget.ListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        show()

        fab.setOnClickListener{
            println("tap")
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

    }

    fun show(){
        val listView = findViewById<ListView>(R.id.todoList)

        val db = Firebase.firestore
        var list = mutableListOf<String>()
        db.collection("todo")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        println("${document.id}: ${document.data}" )
                        println((document.data).get("todo"))
                        println((document.data).get("date"))
                        list.add((document.data).get("todo") as String)
                        println("LIST: ${list}")
                    }
                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list)
                    listView.adapter = adapter
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: ${exception}")
                }
    }

}


