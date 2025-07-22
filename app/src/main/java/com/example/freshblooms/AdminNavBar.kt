package com.example.freshblooms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.freshblooms.api.AddProductFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminNavBar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_nav_bar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.admin_bottom_nav)

        // Default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, AdminDashboardFragment())
            .commit()

        // Handle navigation item selection
        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.dashboard -> AdminDashboardFragment()
                R.id.addproduct -> AddProductFragment()
                R.id.transcation -> TransactionHistoryFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, it)
                    .commit()
                true
            } ?: false
        }
    }
}
