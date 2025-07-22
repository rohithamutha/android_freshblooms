package com.example.freshblooms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import com.example.freshblooms.api.SignupRequest
import com.example.freshblooms.api.SignupResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etPhone = findViewById(R.id.phone)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val phone = etPhone.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                signupUser(username, email, password, phone)
            }
        }
    }

    private fun signupUser(name: String, email: String, password: String, phone: String) {
        val api = ApiClient.instance.create(ApiService::class.java)
        val request = SignupRequest(name, email, password, phone)

        api.signup(request).enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    if (res != null && res.status) {
                        Toast.makeText(this@CreateAccountActivity, res.message, Toast.LENGTH_SHORT).show()
                        // Navigate to login
                        startActivity(Intent(this@CreateAccountActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@CreateAccountActivity, res?.message ?: "Signup failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errBody = response.errorBody()?.string()
                    Toast.makeText(this@CreateAccountActivity, "Server error"+errBody, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Toast.makeText(this@CreateAccountActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
