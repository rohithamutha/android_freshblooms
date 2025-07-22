package com.example.freshblooms

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.freshblooms.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HomeFragment()).commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {

                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HomeFragment()).commit()
                    true
                }
                R.id.nav_cart -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,CartFragment()).commit()
                    true
                }
                R.id.nav_order -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,OrderFragment()).commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
}