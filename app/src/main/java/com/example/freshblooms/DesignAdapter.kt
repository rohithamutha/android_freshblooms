package com.example.freshblooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freshblooms.api.ApiClient.IMAGE_URL

class DesignAdapter(private var designList: List<FlowerDesign>) :
    RecyclerView.Adapter<DesignAdapter.DesignViewHolder>() {

    inner class DesignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val designImage: ImageView = itemView.findViewById(R.id.designImage)
        val designName: TextView = itemView.findViewById(R.id.designName)
        val designPrice: TextView = itemView.findViewById(R.id.designPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return DesignViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesignViewHolder, position: Int) {
        val design = designList[position]

        // Load image using Glide from URL or resource name
        Glide.with(holder.itemView.context)
            .load(IMAGE_URL+"${design.imageResId}")
            .placeholder(R.drawable.dahlias)  // Optional: add a default image
            .into(holder.designImage)

        holder.designName.text = design.name
        holder.designPrice.text = design.price
    }

    override fun getItemCount(): Int = designList.size

    // 🔧 Add this function to update the list dynamically
    fun updateList(newList: List<FlowerDesign>) {
        designList = newList
        notifyDataSetChanged()
    }
}
