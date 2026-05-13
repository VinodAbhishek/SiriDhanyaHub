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

class DirectBuyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_direct_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_open_about).setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_direct_buy)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val farmers = listOf(
            FarmerProduct("Ramesh Kumar", "Ragi (Finger Millet)", "50 kg", "35", "Davangere, Karnataka", "9876543210", true),
            FarmerProduct("Suresh Naik", "Navane (Foxtail Millet)", "80 kg", "42", "Haveri, Karnataka", "9845612340", true),
            FarmerProduct("Manjunath G", "Sajje (Pearl Millet)", "100 kg", "28", "Ballari, Karnataka", "9741236540", false),
            FarmerProduct("Lakshmi Devi", "Baragu (Sorghum)", "60 kg", "22", "Raichur, Karnataka", "9632587410", true),
            FarmerProduct("Venkatesh R", "Oodalu (Kodo Millet)", "40 kg", "55", "Mysuru, Karnataka", "9512348760", true),
            FarmerProduct("Basavraj M", "Ragi (Finger Millet)", "120 kg", "32", "Chitradurga, Karnataka", "9988776655", false),
            FarmerProduct("Anitha S", "Navane (Foxtail Millet)", "70 kg", "40", "Dharwad, Karnataka", "9123456780", true)
        )

        recycler.adapter = DirectBuyAdapter(farmers, requireContext())
    }
}