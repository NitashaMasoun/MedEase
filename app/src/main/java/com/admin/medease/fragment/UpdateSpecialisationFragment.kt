package com.admin.medease.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.admin.medease.databinding.FragmentUpdateSpecialisationBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateSpecialisationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateSpecialisationFragment : Fragment() {
    lateinit var binding: FragmentUpdateSpecialisationBinding
    var db = Firebase.firestore
    var CatId = ""
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            CatId = it.getString("CatId").toString()
            println("CatId: $CatId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateSpecialisationBinding.inflate(layoutInflater)
        binding.btnAdd.setOnClickListener {
            val category = hashMapOf(
                "name" to binding.Category.text.toString()
            )
            db.collection("Specialties").document(CatId).set(category)
                .addOnSuccessListener {
                    Toast.makeText(requireActivity(), "Category Update", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireActivity(),
                        "Category not Update" + it.message,
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
         * @return A new instance of fragment UpdateSpecialisationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateSpecialisationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
