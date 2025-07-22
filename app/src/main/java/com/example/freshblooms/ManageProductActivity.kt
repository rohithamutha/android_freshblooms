package com.example.freshblooms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ManageProductActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var btnAddRow: Button

    private val productList = mutableListOf(
        Product("Blanket Flower", "flowers", R.drawable.flower, 120, 10, 50, "2 Days", "summer"),
        Product("Musk Rose", "flowers", R.drawable.logo, 130, 5, 25, "2 Days", "summer"),
        Product("Chrysanthemums", "flowers", R.drawable.dahlias, 180, 3, 30, "2 Days", "summer"),
        Product("Bougainvillea", "flowers", R.drawable.logo, 150, 6, 50, "2 Days", "summer"),
        Product("Globe Amaranth", "flowers", R.drawable.dahlias, 120, 8, 10, "2 Days", "summer"),
        Product("Aster", "flowers", R.drawable.flower, 100, 7, 25, "2 Days", "winter")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)

        // Initialize Views
        recyclerView = findViewById(R.id.productRecyclerView)
        btnAddRow = findViewById(R.id.btnAddRow)

        // Setup Adapter with edit and delete click listeners
        adapter = ProductAdapter(
            productList,
            onEditClick = { product ->
                Toast.makeText(this, "Edit ${product.name}", Toast.LENGTH_SHORT).show()
                // TODO: Navigate to EditProductActivity if required
            },
            onDeleteClick = { product ->
                productList.remove(product)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Deleted ${product.name}", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAddRow.setOnClickListener {
            Toast.makeText(this, "Add Row clicked", Toast.LENGTH_SHORT).show()
            // TODO: Launch AddProductActivity
            // Example: startActivity(Intent(this, AddProductActivity::class.java))
        }
    }
}
