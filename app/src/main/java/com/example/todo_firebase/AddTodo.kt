package com.example.todo_firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddTodo {

    val db = Firebase.firestore

    fun addTodo(todo: String, date: String){
        val todoData = hashMapOf<String, String>(
                "todo" to todo,
                "date" to date
        )

        db.collection("users")
                .add(todoData)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

    }

}