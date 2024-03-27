package com.admin.medease.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.admin.medease.databinding.FragmentAddSpecialisationBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class AddSpecialisationFragment : Fragment() {
    lateinit var binding : FragmentAddSpecialisationBinding
    var db = Firebase.firestore
    var CatId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            CatId = it.getString("CatId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddSpecialisationBinding.inflate(layoutInflater)
        binding.btnAdd.setOnClickListener {



            val category = hashMapOf(
                "name" to binding.Category.text.toString()
            )
            db.collection("Specialties").add(category)
                .addOnSuccessListener {
                    Toast.makeText(requireActivity(), "Category added", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireActivity(),
                        "Category not added" + it.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSpecialisationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}