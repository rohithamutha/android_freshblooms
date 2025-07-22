package com.example.freshblooms

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdminOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false)
        recyclerView = view.findViewById(R.id.rvAdminOrders)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAdminOrders()
    }

    private fun fetchAdminOrders() {
        val api = ApiClient.instance.create(ApiService::class.java)
        api.getAdminOrders().enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    val orders = response.body()?.orders ?: emptyList()
                    adapter = AdminOrderAdapter(orders)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "No orders found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
