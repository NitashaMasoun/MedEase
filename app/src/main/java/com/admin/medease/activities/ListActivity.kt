package com.admin.medease.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.admin.medease.R
import com.admin.medease.model.SpecialisationModel
import com.admin.medease.adapter.SpecialisationAdapter
import com.admin.medease.databinding.ActivityListBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    var db = Firebase.firestore
    lateinit var navController :NavController
    lateinit var specialisationAdapter: SpecialisationAdapter
    var specialisationList = ArrayList<SpecialisationModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragment)




    }
}