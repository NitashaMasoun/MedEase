package com.admin.medease.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.admin.medease.DoctorModel

import com.admin.medease.R
import com.admin.medease.adapter.DoctorAdapter
import com.admin.medease.databinding.FragmentDoctorBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * A simple [Fragment] subclass.
 * Use the [DoctorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoctorFragment : Fragment() {
    lateinit var binding: FragmentDoctorBinding
    var db = Firebase.firestore
    lateinit var doctorAdapter: DoctorAdapter
    var doctorList = ArrayList<DoctorModel>()

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
        binding = FragmentDoctorBinding.inflate(layoutInflater)
        doctorList.clear()
        db.collection("Doctors").get().addOnSuccessListener {
            for (document in it.documentChanges) {
                var model = document.document.toObject(DoctorModel::class.java)
                model.id = document.document.id
                doctorList.add(model)
                println("Doctor: $doctorList")
                doctorAdapter.notifyDataSetChanged()

            }
        }
        doctorAdapter = DoctorAdapter(doctorList)
        binding.lvList.layoutManager = LinearLayoutManager(requireActivity())
        binding.lvList.adapter = doctorAdapter
        binding.fabBtn.setOnClickListener {
            findNavController().navigate(R.id.doctorFragmentAdd)
        }
        return binding.root
    }
}
