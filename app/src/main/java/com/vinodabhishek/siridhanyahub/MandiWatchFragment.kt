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

class MandiWatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mandi_watch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_open_calculator).setOnClickListener {
            val intent = Intent(requireContext(), CalculatorActivity::class.java)
            startActivity(intent)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_mandi)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val priceData = listOf(
            MilletPrice("Navane (Foxtail)", "Davangere", 2800, 3100, 2600, "↑"),
            MilletPrice("Navane (Foxtail)", "Bengaluru", 2950, 3200, 2700, "↑"),
            MilletPrice("Sajje (Pearl)", "Davangere", 2200, 2500, 2000, "↓"),
            MilletPrice("Sajje (Pearl)", "Bengaluru", 2350, 2600, 2100, "↑"),
            MilletPrice("Baragu (Sorghum)", "Davangere", 1900, 2100, 1700, "↓"),
            MilletPrice("Baragu (Sorghum)", "Bengaluru", 2050, 2300, 1850, "↑"),
            MilletPrice("Ragi (Finger)", "Davangere", 3200, 3500, 2900, "↑"),
            MilletPrice("Ragi (Finger)", "Bengaluru", 3400, 3700, 3100, "↑"),
            MilletPrice("Oodalu (Kodo)", "Davangere", 4500, 4800, 4200, "↓"),
            MilletPrice("Oodalu (Kodo)", "Bengaluru", 4700, 5000, 4400, "↑")
        )

        recycler.adapter = MandiAdapter(priceData, requireContext())
    }
}