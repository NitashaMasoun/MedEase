package com.admin.medease.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.admin.medease.databinding.ActivitySpecializationBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class SpecializationActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpecializationBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecializationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            val category = hashMapOf(
                "name" to binding.Category.text.toString()
            )
            db.collection("Specialties").add(category)
                .addOnSuccessListener {
                    Toast.makeText(this, "Category added", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Category not added" + it.message, Toast.LENGTH_SHORT)
                        .show()

                }
        }


    }
}

















