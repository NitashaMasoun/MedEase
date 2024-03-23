package com.admin.medease.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.admin.medease.R
import com.admin.medease.model.SpecialisationModel
import com.admin.medease.adapter.SpecialisationAdapter
import com.admin.medease.clickinterface.ClickInterface
import com.admin.medease.databinding.FragmentSpecialisationBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * A simple [Fragment] subclass.
 * Use the [SpecialisationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecialisationFragment : Fragment(), ClickInterface {
    // TODO: Rename and change types of parameter
    lateinit var binding: FragmentSpecialisationBinding

    var db = Firebase.firestore
    lateinit var specialisationAdapter: SpecialisationAdapter
    var specialisationList = ArrayList<SpecialisationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpecialisationBinding.inflate(layoutInflater)
        specialisationAdapter = SpecialisationAdapter(specialisationList, this)
        binding.lvList.layoutManager = LinearLayoutManager(requireActivity())
        binding.lvList.adapter = specialisationAdapter
        db.collection("Specialties").get().addOnSuccessListener {
            for (document in it.documentChanges) {
                var model = document.document.toObject(SpecialisationModel::class.java)
                model.id = document.document.id
                specialisationList.add(model)
                println("Specialisation: $specialisationList")
                specialisationAdapter.notifyDataSetChanged()

            }
        }
        binding.fabBtn.setOnClickListener {
            findNavController().navigate(R.id.addSpecialisationFragment)
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
         * @return A new instance of fragment SpecialisationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpecialisationFragment().apply {
                arguments = Bundle().apply {
                }
                }

            }


    override fun onClick(specialisationModel: SpecialisationModel) {
        println("OnClick: $specialisationModel")
        findNavController().navigate(
            R.id.updateSpecialisationFragment,
            bundleOf("CatId" to specialisationModel.id)
        )

    }

    override fun onDelete(specialisationModel: SpecialisationModel) {
        db.collection("Specialties").document(specialisationModel.id.toString()).delete()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireActivity(), "Category Update", Toast.LENGTH_SHORT).show()

                }
            }.addOnFailureListener {
            Toast.makeText(
                requireActivity(),
                "Category not Update" + it.message,
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onNextClick(specialisationModel: SpecialisationModel) {
            println("OnNextClick: $specialisationModel")
            findNavController().navigate(
                R.id.addSpecialisationFragment,
                bundleOf("CatId" to specialisationModel.id)
            )

        }
    }