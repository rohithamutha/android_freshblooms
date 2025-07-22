package com.example.freshblooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freshblooms.api.ApiClient.IMAGE_URL
import com.example.freshblooms.api.FlowerItem

class FlowerAdapter(
    private var flowerList: List<FlowerItem>,
    private val onFlowerClick: (FlowerItem) -> Unit
) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flowerImage: ImageView = itemView.findViewById(R.id.flowerImage)
        val flowerName: TextView = itemView.findViewById(R.id.flowerName)
        val flowerPrice: TextView = itemView.findViewById(R.id.flowerPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flower, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = flowerList[position]

        Glide.with(holder.itemView.context)
            .load(IMAGE_URL + flower.image)
            .placeholder(R.drawable.dahlias)
            .into(holder.flowerImage)

        holder.flowerName.text = flower.flowername
        holder.flowerPrice.text = flower.price

        holder.flowerImage.setOnClickListener {
            onFlowerClick(flower)
        }
    }

    override fun getItemCount(): Int = flowerList.size

    fun updateList(newList: List<FlowerItem>) {
        flowerList = newList
        notifyDataSetChanged()
    }
}
