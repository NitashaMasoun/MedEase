package com.admin.medease.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.admin.medease.DoctorDashboard
import com.admin.medease.R
import com.admin.medease.activities.ListActivity
import com.admin.medease.activities.LoginActivity
import com.admin.medease.databinding.ActivityDoctorLoginBinding
import com.admin.medease.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class DoctorLogin : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityDoctorLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            auth = Firebase.auth
            binding.btnLogin.setOnClickListener {
                var email = binding.edtEmail.text.toString()
                var password = binding.edtPassword.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            startActivity(Intent(this,  DoctorDashboard::class.java))
                            this.finish()
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
    }