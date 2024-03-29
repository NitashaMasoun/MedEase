package com.admin.medease.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.admin.medease.adapter.DoctorAdapter
import com.admin.medease.databinding.AddFragmentDoctorBinding
import com.admin.medease.databinding.FragmentDoctorBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

/**
 * A simple [Fragment] subclass.
 * Use the [DoctorFragmentAdd.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoctorFragmentAdd : Fragment() {
    lateinit var binding: AddFragmentDoctorBinding
    var auth = Firebase.auth

    var db = Firebase.firestore
    lateinit var DoctorAdapter : DoctorAdapter

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
        binding = AddFragmentDoctorBinding.inflate(layoutInflater)
        binding.btnAdd.setOnClickListener {


            val category = hashMapOf(
                "name" to binding.etCategory.text.toString(),
                "email" to binding.edtEmail.text.toString(),
                "password" to binding.edtPassword.text.toString(),
                "description" to binding.etdescription.text.toString(),
                "catId" to CatId
            )
            val email=binding.edtEmail.text.toString()
            val password=binding.edtPassword.text.toString()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    db.collection("Doctors").add(category)
                        .addOnSuccessListener {
                            findNavController().popBackStack()
                            Toast.makeText(requireActivity(), "Category added", Toast.LENGTH_SHORT).show()

                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                requireActivity(),
                                "Category not added" + it.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            // Inflate the layout for this fragment

                        }
                }
            }

        }

        return binding.root
    }
}
