package com.example.freshblooms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_transaction_history)

        val transactionList = listOf(
            TransactionItem("#pay_PhDbSbWOc2TYas", "Wed, 8 Jan", "₹690", "Completed"),
            TransactionItem("#pay_PhDbSbWOc2g1wR", "Thu, 9 Jan", "₹1420", "Completed"),
            TransactionItem("#pay_PhDbSbWOc2gTT", "Thu, 9 Jan", "₹1780", "Completed"),
            TransactionItem("#pay_PhDYbWOc2gnhY", "Thu, 9 Jan", "₹330", "Completed"),
            TransactionItem("#pay_PhDbSbWOc2gnhZ", "Thu, 9 Jan", "₹280", "Completed"),
            TransactionItem("#pay_PhDyuZti17YLpt", "Thu, 9 Jan", "₹530", "Completed"),
            TransactionItem("#pay_PhEcHn2O0h7Bw7", "Thu, 9 Jan", "₹300", "Completed"),
            TransactionItem("#pay_PhEjBdILZ1jAPK", "Thu, 9 Jan", "₹150", "Completed")
        )

//        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
//        transactionRecyclerView.adapter = TransactionAdapter(transactionList)
    }
}
