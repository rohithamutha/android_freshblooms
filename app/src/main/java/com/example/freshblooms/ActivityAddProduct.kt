package com.example.freshblooms

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.graphics.drawable.Drawable
import android.view.View

class ActivityAddProduct : AppCompatActivity() {

    private lateinit var flowerName: EditText
    private lateinit var flowerDescription: EditText
    private lateinit var flowerPrice: EditText
    private lateinit var flowerSeason: Spinner
    private lateinit var flowerDesign: Spinner
    private lateinit var selectImages: Button
    private lateinit var submit: Button
    private lateinit var imagePreviewContainer: LinearLayout

    private val selectedImageUris = ArrayList<Uri>()
    private val STORAGE_PERMISSION_REQUEST_CODE = 1001

    // Activity result launcher for image selection
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUris.clear()
                val data = result.data

                if (data?.clipData != null) {
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val uri = data.clipData!!.getItemAt(i).uri
                        selectedImageUris.add(uri)
                    }
                } else if (data?.data != null) {
                    selectedImageUris.add(data.data!!)
                }

                displaySelectedImages()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_add_product)

        flowerName = findViewById(R.id.etName)
        flowerDescription = findViewById(R.id.etDescription)
        flowerPrice = findViewById(R.id.etPrice)
        flowerSeason = findViewById(R.id.spinnerSeason)
        flowerDesign = findViewById(R.id.spinnerCategory)
        selectImages = findViewById(R.id.btnChooseImage)
        submit = findViewById(R.id.btnSubmit)
        imagePreviewContainer = findViewById(R.id.imagePreviewContainer)

        checkStoragePermission()

        selectImages.setOnClickListener {
            if (hasStoragePermission()) {
                openImagePicker()
            } else {
                checkStoragePermission()
            }
        }

        submit.setOnClickListener {
            uploadProduct()
        }
    }

    private fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun checkStoragePermission() {
        if (!hasStoragePermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    STORAGE_PERMISSION_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        imagePickerLauncher.launch(Intent.createChooser(intent, "Select Pictures"))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker()
            } else {
                Toast.makeText(this, "Permission denied. Cannot select images.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displaySelectedImages() {
        imagePreviewContainer.removeAllViews()

        val layoutParams = LayoutParams(200, 200)
        layoutParams.setMargins(8, 8, 8, 8)

        for (uri in selectedImageUris) {
            val imageView = ImageView(this)
            imageView.layoutParams = layoutParams
            imageView.setImageURI(uri)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imagePreviewContainer.addView(imageView)
        }

        Toast.makeText(this, "${selectedImageUris.size} image(s) selected", Toast.LENGTH_SHORT).show()
    }

    private fun uploadProduct() {
        val name = flowerName.text.toString()
        val description = flowerDescription.text.toString()
        val price = flowerPrice.text.toString()
        val season = flowerSeason.selectedItem.toString()
        val design = flowerDesign.selectedItem.toString()

        if (name.isEmpty() || description.isEmpty() || price.isEmpty() || selectedImageUris.isEmpty()) {
            Toast.makeText(this, "Please fill all fields and select images", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Implement actual upload logic
        Toast.makeText(this, "Product uploaded successfully (dummy)", Toast.LENGTH_SHORT).show()
    }
}
