package com.example.freshblooms

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityTransactionHistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_transaction_history)

        recyclerView = findViewById(R.id.rvTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
                    Toast.makeText(this@ActivityTransactionHistory, "No data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TransactionHistoryResponse>, t: Throwable) {
                Toast.makeText(this@ActivityTransactionHistory, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
