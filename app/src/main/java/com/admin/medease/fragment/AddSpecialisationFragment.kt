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

/**
 * A simple [Fragment] subclass.
 * Use the [AddSpecialisationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddSpecialisationFragment : Fragment() {
    lateinit var binding : FragmentAddSpecialisationBinding
    var db = Firebase.firestore
    var CatId = ""
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddSpecialisationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSpecialisationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}