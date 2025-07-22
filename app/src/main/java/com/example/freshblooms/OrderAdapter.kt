package com.example.freshblooms

import Order
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freshblooms.api.ApiClient.IMAGE_URL

class OrderAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.orderImage)
        val name: TextView = view.findViewById(R.id.orderName)
        val details: TextView = view.findViewById(R.id.orderDetails)
        val address: TextView = view.findViewById(R.id.orderAddress)
        val date: TextView = view.findViewById(R.id.orderDate)
        val status: TextView = view.findViewById(R.id.orderStatus)
        val resultButton: Button = view.findViewById(R.id.orderSuccessBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        val imageUrl = "$IMAGE_URL${order.image}"  // Use the same constant as CartAdapter
        Glide.with(holder.image.context)
            .load(imageUrl)
            .placeholder(R.drawable.dahlias) // Same placeholder as CartAdapter
            .into(holder.image)

        holder.name.text = order.flowername
        holder.details.text = "Rs. ${order.price} | Qty: ${order.quantity}"
        holder.address.text = order.address
        holder.date.text = order.delivery_date
        holder.status.text = order.status
        holder.resultButton.text = order.status

        // Optional: Status-based color change
        val context = holder.resultButton.context
        val successColor = ContextCompat.getColor(context, R.color.green)  // define in colors.xml
        val failColor = ContextCompat.getColor(context, R.color.red)          // define in colors.xml

        if (order.status.equals("Success", ignoreCase = true)) {
            holder.resultButton.setBackgroundColor(successColor)
        } else {
            holder.resultButton.setBackgroundColor(failColor)
        }
    }


    override fun getItemCount(): Int = orders.size
}
