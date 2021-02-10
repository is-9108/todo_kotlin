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

        val listView = findViewById<ListView>(R.id.todoList)

        val list = show()


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list)
        listView.adapter = adapter



        fab.setOnClickListener{
            println("tap")
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

    }

    fun show() :List<String>{
        val db = Firebase.firestore
        var list = mutableListOf<String>()
        db.collection("todo")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
//                        Log.d(TAG, "${document.id} => ${document.data}")
                        println("${document.id}: ${document.data}" )
                        println((document.data).get("todo"))
                        println((document.data).get("date"))
                        list.add((document.data).get("todo") as String)
                        println("LIST: ${list}")
                    }
                }
                .addOnFailureListener { exception ->
//                    Log.d(TAG, "Error getting documents: ", exception)
                    println("Error getting documents: ${exception}")
                }
        println(list)
        return list
    }
}


