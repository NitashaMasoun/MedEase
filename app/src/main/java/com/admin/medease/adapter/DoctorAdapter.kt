package com.admin.medease.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admin.medease.DoctorModel
import com.admin.medease.R
import com.admin.medease.clickinterface.ClickInterface
import com.admin.medease.model.SpecialisationModel

class DoctorAdapter(private  var doctorList: List<DoctorModel>):
    RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {
        class ViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
            var tvName: TextView = view.findViewById<TextView>(R.id.tvName)
            var btnUpdate: ImageView = view.findViewById<ImageView>(R.id.btnUpdate)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val doctorItemActivity = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_item, parent, false)
            return ViewHolder(doctorItemActivity)
        }

        override fun getItemCount(): Int {
            return doctorList.size

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemActivity = doctorList[position].name
            holder.tvName.setText(itemActivity)
            holder.btnUpdate.setOnClickListener {
            }
        }
    }
