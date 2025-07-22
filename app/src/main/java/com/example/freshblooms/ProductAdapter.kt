package com.example.freshblooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onEditClick: (Product) -> Unit,
    private val onDeleteClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val name = view.findViewById<TextView>(R.id.tvName)
        val category = view.findViewById<TextView>(R.id.tvCategory)
        val price = view.findViewById<TextView>(R.id.tvPrice)
        val stock = view.findViewById<TextView>(R.id.tvStock)
        val offer = view.findViewById<TextView>(R.id.tvOffer)
        val delivery = view.findViewById<TextView>(R.id.tvDelivery)
        val season = view.findViewById<TextView>(R.id.tvSeason)
        val edit = view.findViewById<ImageButton>(R.id.btnEdit)
        val delete = view.findViewById<ImageButton>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_row_product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.name.text = "Name: ${product.name}"
        holder.category.text = "Category: ${product.category}"
        holder.price.text = "Price: ₹${product.price}"
        holder.stock.text = "Stock: ${product.stock}"
        holder.offer.text = "Offer: ${product.offer}%"
        holder.delivery.text = "Delivery: ${product.delivery}"
        holder.season.text = "Season: ${product.season}"
        holder.image.setImageResource(product.imageRes) // Or use Glide

        holder.edit.setOnClickListener { onEditClick(product) }
        holder.delete.setOnClickListener { onDeleteClick(product) }
    }
}
