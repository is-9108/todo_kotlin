package com.example.todo_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        show()

        fab.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        show()
    }

    fun show(){
        val listView = findViewById<ListView>(R.id.todoList)

        val db = Firebase.firestore
        var todoList = mutableListOf<String>()
        var idList = mutableListOf<String>()
        db.collection("todo")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        println(document.id)
                        todoList.add((document.data).get("todo") as String)
                        idList.add(document.id)
                    }
                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoList)
                    listView.adapter = adapter
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: ${exception}")
                }

        listView.setOnItemClickListener { parent, view, position, id ->
            println(todoList[id.toInt()])
            println(idList[id.toInt()])
            db.collection("todo").document(idList[id.toInt()]).delete()
                    .addOnSuccessListener {
                        println("delete success")
                        todoList.removeAt(id.toInt())
                        idList.removeAt(id.toInt())
                        for (todo in todoList){
                            println("todo:${todo}")
                            show()
                        }
                        for (id in idList){
                            println("id: ${id}")
                        }
                    }
                    .addOnFailureListener { e -> println("error: ${e}") }
        }
    }



}


