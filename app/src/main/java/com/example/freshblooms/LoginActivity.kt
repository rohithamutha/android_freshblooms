package com.example.freshblooms

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import com.example.freshblooms.api.LoginRequest
import com.example.freshblooms.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var signUpText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        signUpText = findViewById(R.id.textView2)

        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }

        signUpText.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val api = ApiClient.instance.create(ApiService::class.java)
        val request = LoginRequest(email, password)

        api.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    if (res != null && res.status) {
                        Toast.makeText(this@LoginActivity, "Welcome ${res.name}", Toast.LENGTH_SHORT).show()

                        PreferenceHelper(applicationContext).saveUserData(res.id.toString(),
                            res.name.toString(), res.email.toString(), res.phone.toString()
                        )
                        // ✅ Navigate to Dashboard
                        val intent = Intent(this@LoginActivity, Dashboard::class.java)
                        intent.putExtra("userId", res.id)
                        intent.putExtra("userName", res.name)
                        intent.putExtra("userType", res.usertype)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, res?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Server error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
