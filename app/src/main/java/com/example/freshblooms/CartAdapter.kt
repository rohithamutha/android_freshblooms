package com.example.freshblooms

import BasicResponse
import CartItem
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAdapter(private val items: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cartName)
        val price: TextView = itemView.findViewById(R.id.cartPrice)
        val total: TextView = itemView.findViewById(R.id.cartTotal)
        val qty: TextView = itemView.findViewById(R.id.quantityText)
        val image: ImageView = itemView.findViewById(R.id.cartImage)
        val btnIncrease: ImageView = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: ImageView = itemView.findViewById(R.id.btnDecrease)
        val btnDelete: ImageView = itemView.findViewById(R.id.cartDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.name.text = item.flowername
        holder.price.text = "₹${item.price}"
        holder.qty.text = item.quantity.toString()
        holder.total.text = "Total: ₹${item.price * item.quantity}"

        val imageUrl = ApiClient.IMAGE_URL + item.image
        Glide.with(holder.image.context)
            .load(imageUrl)
            .placeholder(R.drawable.dahlias)
            .into(holder.image)

        holder.btnIncrease.setOnClickListener {
            item.quantity++
            holder.qty.text = item.quantity.toString()
            holder.total.text = "Total: ₹${item.price * item.quantity}"
            notifyItemChanged(position)
        }

        holder.btnDecrease.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                holder.qty.text = item.quantity.toString()
                holder.total.text = "Total: ₹${item.price * item.quantity}"
                notifyItemChanged(position)
            } else {
                Toast.makeText(holder.itemView.context, "Minimum quantity is 1", Toast.LENGTH_SHORT).show()
            }
        }

        holder.btnDelete.setOnClickListener {
            val context = holder.itemView.context
            val api = ApiClient.instance.create(ApiService::class.java)
            val body = mapOf("cid" to item.cid)

            Log.d("CartAdapter", "Deleting cart item with cid = ${item.cid}")

            api.deleteCartItem(body).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    if (response.isSuccessful && response.body()?.status == "success") {
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                        items.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, items.size)
                    } else {
                        Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show()
                        Log.e("CartAdapter", "Error response: ${response.body()?.message}")
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.e("CartAdapter", "API Failure", t)
                }
            })
        }
    }

    override fun getItemCount() = items.size
}
