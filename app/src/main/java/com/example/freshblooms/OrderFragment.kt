package com.example.freshblooms

import Order
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import retrofit2.Call
import retrofit2.Response

class OrderFragment : Fragment() {

    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private val orderList = mutableListOf<Order>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        orderRecyclerView = view.findViewById(R.id.orderRecyclerView)
        orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        orderAdapter = OrderAdapter(orderList)
        orderRecyclerView.adapter = orderAdapter

//        val sharedPref = requireContext().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val userId = PreferenceHelper(requireContext()).getUserId()

        if (userId != "") {
            fetchOrders(userId)
        }

        return view
    }

    private fun fetchOrders(userId: String) {
        val call = ApiClient.instance.create(ApiService::class.java).getUserOrders(userId)

        call.enqueue(object : retrofit2.Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    orderList.clear()
                    orderList.addAll(response.body()!!.orders)
                    orderAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
