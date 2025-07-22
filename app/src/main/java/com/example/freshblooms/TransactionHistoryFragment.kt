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

class TransactionHistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history, container, false)
        recyclerView = view.findViewById(R.id.rvTransactions)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTransactionData()
    }

    private fun fetchTransactionData() {
        val api = ApiClient.instance.create(ApiService::class.java)
        api.getTransactionHistory().enqueue(object : Callback<TransactionHistoryResponse> {
            override fun onResponse(
                call: Call<TransactionHistoryResponse>,
                response: Response<TransactionHistoryResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    val transactions = response.body()?.transactions ?: emptyList()
                    adapter = TransactionAdapter(transactions)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TransactionHistoryResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
