package com.example.freshblooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeaturedAdapter(private val list: List<FeaturedProduct>) :
    RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    inner class FeaturedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.featureName)
        val price: TextView = view.findViewById(R.id.featurePrice)
        val image: ImageView = view.findViewById(R.id.featureImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured_list, parent, false)
        return FeaturedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val product = list[position]
        holder.name.text = product.name
        holder.price.text = "Rs.${product.price}"
        holder.image.setImageResource(product.imageResId)
    }

    override fun getItemCount(): Int = list.size
}
