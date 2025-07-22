package com.example.freshblooms

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var nameField: EditText
    private lateinit var priceField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var offerField: EditText
    private lateinit var deliveryField: EditText
    private lateinit var stockField: EditText
    private lateinit var spinnerSeason: Spinner
    private lateinit var spinnerCategory: Spinner
    private lateinit var chooseImageBtn: Button
    private lateinit var submitBtn: Button

    private val seasons = arrayOf("All Season", "Spring", "Summer", "Autumn", "Monsoon", "Winter")
    private val categories = arrayOf("Flowers", "Bouquets", "Designs")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_product)

        nameField = findViewById(R.id.etName)
        priceField = findViewById(R.id.etPrice)
        descriptionField = findViewById(R.id.etDescription)
        offerField = findViewById(R.id.etOffer)
        deliveryField = findViewById(R.id.etDelivery)
        stockField = findViewById(R.id.etStock)
        spinnerSeason = findViewById(R.id.spinnerSeason)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        chooseImageBtn = findViewById(R.id.btnChooseImage)
        submitBtn = findViewById(R.id.btnSubmit)

        // Set dropdown options
        spinnerSeason.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, seasons)
        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        chooseImageBtn.setOnClickListener {
            Toast.makeText(this, "Image picker not yet implemented", Toast.LENGTH_SHORT).show()
        }

        submitBtn.setOnClickListener {
            val name = nameField.text.toString()
            val price = priceField.text.toString()
            val desc = descriptionField.text.toString()
            val offer = offerField.text.toString()
            val delivery = deliveryField.text.toString()
            val stock = stockField.text.toString()
            val season = spinnerSeason.selectedItem.toString()
            val category = spinnerCategory.selectedItem.toString()

            // TODO: Send data to backend or Firebase
            Toast.makeText(this, "Product '$name' submitted", Toast.LENGTH_LONG).show()
        }
    }
}
