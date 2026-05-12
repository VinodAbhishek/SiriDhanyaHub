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
                "Finger Millet", "Ragi 🟤",
                "💪",
                "Highest calcium among all cereals",
                "Controls blood sugar levels",
                "Great for bone strength & growth",
                "Low (54)"
            ),
            HealthBenefit(
                "Foxtail Millet", "Navane 🌾",
                "🧠",
                "Boosts brain & nervous system health",
                "Rich in iron & B vitamins",
                "Helps manage diabetes",
                "Low (50)"
            ),
            HealthBenefit(
                "Pearl Millet", "Sajje 🌿",
                "❤️",
                "Reduces bad cholesterol (LDL)",
                "High in magnesium for heart health",
                "Good source of plant protein",
                "Medium (55)"
            ),
            HealthBenefit(
                "Sorghum", "Baragu ☀️",
                "🛡️",
                "Rich in antioxidants",
                "Gluten-free & gut friendly",
                "Supports weight management",
                "Low (50)"
            ),
            HealthBenefit(
                "Kodo Millet", "Oodalu 🌱",
                "🔋",
                "High in dietary fiber",
                "Manages blood pressure",
                "Boosts energy & reduces fatigue",
                "Low (45)"
            )
        )

        recycler.adapter = HealthAdapter(healthData)
    }
}