package com.vinodabhishek.siridhanyahub

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        view.findViewById<Button>(R.id.btn_open_calendar).setOnClickListener {
            val intent = Intent(requireContext(), SeasonalCalendarActivity::class.java)
            startActivity(intent)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_health)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val milletList = listOf(
            HealthBenefit("Finger Millet", "Ragi 🟤", "💪",
                "Rich in calcium — stronger bones", "Controls blood sugar levels",
                "High fiber aids digestion", "Glycemic Index: Low (54)",
                7.3f, 72.6f, 11.5f, 8.6f),
            HealthBenefit("Foxtail Millet", "Navane 🟡", "🌟",
                "Lowers bad cholesterol", "Rich in iron — prevents anemia",
                "Boosts immunity", "Glycemic Index: Very Low (50)",
                12.3f, 60.9f, 8.0f, 4.4f),
            HealthBenefit("Pearl Millet", "Sajje ⚪", "⚡",
                "High protein for muscle building", "Rich in iron and zinc",
                "Boosts energy levels", "Glycemic Index: Medium (55)",
                11.6f, 61.8f, 1.2f, 2.3f),
            HealthBenefit("Sorghum", "Baragu 🟠", "🌿",
                "Gluten-free — safe for celiac", "Rich in antioxidants",
                "Good for heart health", "Glycemic Index: Low (50)",
                10.4f, 64.7f, 6.3f, 1.6f),
            HealthBenefit("Kodo Millet", "Oodalu 🟢", "🩸",
                "Best for diabetes management", "Detoxifies the body",
                "Rich in polyphenols", "Glycemic Index: Very Low (52)",
                9.8f, 65.9f, 9.0f, 2.6f)
        )

        recycler.adapter = HealthAdapter(milletList, requireContext())
    }
}