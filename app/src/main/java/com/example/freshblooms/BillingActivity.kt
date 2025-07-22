package com.example.freshblooms

import CartItem
import CartResponse
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillingActivity : AppCompatActivity(), PaymentResultListener {

    private lateinit var billingRecyclerView: RecyclerView
    private lateinit var billingAdapter: CartAdapter
    private val cartList = mutableListOf<CartItem>()
    private lateinit var placeOrderButton: Button

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var address: EditText
    private lateinit var city: EditText
    private lateinit var country: EditText
    private lateinit var pincode: EditText
    private lateinit var mobile: EditText
    private lateinit var email: EditText
    private lateinit var total: TextView

    private var totalAmount = 0
    private lateinit var userId: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        Checkout.preload(applicationContext)

        billingRecyclerView = findViewById(R.id.billingRecyclerView)
        placeOrderButton = findViewById(R.id.placeOrderButton)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        address = findViewById(R.id.address)
        city = findViewById(R.id.city)
        country = findViewById(R.id.country)
        pincode = findViewById(R.id.pincode)
        mobile = findViewById(R.id.mobile)
        email = findViewById(R.id.email)
        total = findViewById(R.id.total)

        billingAdapter = CartAdapter(cartList)
        billingRecyclerView.layoutManager = LinearLayoutManager(this)
        billingRecyclerView.adapter = billingAdapter

        userId = PreferenceHelper(this).getUserId()

        if (userId.isNotEmpty()) {
            fetchBillingCart(userId)
        }

        placeOrderButton.setOnClickListener {
            startPayment()
        }
    }

    private fun fetchBillingCart(userId: String) {
        val call = ApiClient.instance.create(ApiService::class.java).getCartItems(userId)

        call.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    cartList.clear()
                    cartList.addAll(response.body()?.cart ?: emptyList())
                    billingAdapter.notifyDataSetChanged()
                    calculateTotal()
                } else {
                    Toast.makeText(this@BillingActivity, "Failed to load billing items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Toast.makeText(this@BillingActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calculateTotal() {
        totalAmount = cartList.sumOf { it.price * it.quantity }.toInt() + 50  // + ₹50 shipping
        total.setText("TOTAL: ${totalAmount - 50}")

    }

    private fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_N4vNKGSBfWjcln")

        val jsonObject = JSONObject()
        jsonObject.put("name", "Fresh Blooms")
        jsonObject.put("description", "Order Payment")
        jsonObject.put("currency", "INR")
        jsonObject.put("amount", (totalAmount * 100).toInt())  // Amount in paise

        val prefill = JSONObject()
        prefill.put("email", email.text.toString())
        prefill.put("contact", mobile.text.toString())

        jsonObject.put("prefill", prefill)

        checkout.open(this, jsonObject)
    }

    override fun onPaymentSuccess(paymentId: String?) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
        placeOrder(paymentId ?: "")
    }

    override fun onPaymentError(code: Int, response: String?) {
        Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_LONG).show()
    }

    private fun placeOrder(paymentId: String) {
        val orderData = HashMap<String, Any>()
        orderData["razorpay_payment_id"] = paymentId
        orderData["total_amount"] = totalAmount
        orderData["user_id"] = userId
        orderData["firstname"] = firstName.text.toString()
        orderData["lastname"] = lastName.text.toString()
        orderData["address"] = address.text.toString()
        orderData["city"] = city.text.toString()
        orderData["country"] = country.text.toString()
        orderData["pincode"] = pincode.text.toString()
        orderData["mobile"] = mobile.text.toString()
        orderData["email"] = email.text.toString()

        val productDetails = cartList.map {
            mapOf(
                "product_id" to it.cid,
                "product_name" to it.flowername,
                "price" to it.price,
                "quantity" to it.quantity
            )
        }

        orderData["product_details"] = productDetails

        val call = ApiClient.instance.create(ApiService::class.java).placeOrder(orderData)
        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful && response.body()?.get("status") == "success") {
                    Toast.makeText(this@BillingActivity, "Order placed successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@BillingActivity, "Order failed!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                Toast.makeText(this@BillingActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
