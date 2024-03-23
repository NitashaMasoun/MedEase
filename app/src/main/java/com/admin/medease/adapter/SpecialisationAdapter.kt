package com.admin.medease.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admin.medease.R
import com.admin.medease.model.SpecialisationModel
import com.admin.medease.clickinterface.ClickInterface

class SpecialisationAdapter(
    private var namesList: List<SpecialisationModel>,
    var clickInterface: ClickInterface
) :
    RecyclerView.Adapter<SpecialisationAdapter.ViewHolder>() {
    class ViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById<TextView>(R.id.tvName)
        var btnUpdate: ImageView = view.findViewById<ImageView>(R.id.btnUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val specialisationItemActivity = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_specialisation_item, parent, false)
        return ViewHolder(specialisationItemActivity)
    }

    override fun getItemCount(): Int {
        return namesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemActivity = namesList[position].name
        holder.tvName.setText(itemActivity)
        holder.btnUpdate.setOnClickListener {
            clickInterface.onClick(namesList[position])

        }
        holder.itemView.setOnLongClickListener {
            clickInterface.onDelete(namesList[position])
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener {
            clickInterface.onNextClick(namesList[position])
        }


    }

}