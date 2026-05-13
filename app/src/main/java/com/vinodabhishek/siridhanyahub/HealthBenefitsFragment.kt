package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HealthBenefitsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_benefits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_health)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val healthData = listOf(
            HealthBenefit(
                "Finger Millet", "Ragi 🟤", "💪",
                "Highest calcium among all cereals",
                "Controls blood sugar levels",
                "Great for bone strength & growth",
                "Low (54)", 7.3f, 72.6f, 11.5f, 8.6f
            ),
            HealthBenefit(
                "Foxtail Millet", "Navane 🌾", "🧠",
                "Boosts brain & nervous system health",
                "Rich in iron & B vitamins",
                "Helps manage diabetes",
                "Low (50)", 12.3f, 63.2f, 14.0f, 10.5f
            ),
            HealthBenefit(
                "Pearl Millet", "Sajje 🌿", "❤️",
                "Reduces bad cholesterol (LDL)",
                "High in magnesium for heart health",
                "Good source of plant protein",
                "Medium (55)", 11.6f, 67.5f, 11.3f, 9.6f
            ),
            HealthBenefit(
                "Sorghum", "Baragu ☀️", "🛡️",
                "Rich in antioxidants",
                "Gluten-free & gut friendly",
                "Supports weight management",
                "Low (50)", 10.4f, 70.7f, 10.2f, 8.7f
            ),
            HealthBenefit(
                "Kodo Millet", "Oodalu 🌱", "🔋",
                "High in dietary fiber",
                "Manages blood pressure",
                "Boosts energy & reduces fatigue",
                "Low (45)", 9.8f, 65.9f, 14.3f, 10.0f
            )
        )

        recycler.adapter = HealthAdapter(healthData, requireContext())
    }
}