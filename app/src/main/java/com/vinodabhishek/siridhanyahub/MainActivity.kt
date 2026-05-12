package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Load default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MandiWatchFragment())
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mandiWatch -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MandiWatchFragment())
                        .commit()
                    true
                }
                R.id.recipeLab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RecipeLabFragment())
                        .commit()
                    true
                }
                R.id.healthBenefits -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HealthBenefitsFragment())
                        .commit()
                    true
                }
                R.id.directBuy -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DirectBuyFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}