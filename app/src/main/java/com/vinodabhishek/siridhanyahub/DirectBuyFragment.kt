package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_direct_buy)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val products = listOf(
            FarmerProduct(
                "Finger Millet", "ರಾಗಿ (Ragi)",
                "Ramesh Gowda", "Davangere, Karnataka",
                45, 200, "Organic A-Grade",
                "9876543210", "ramesh.gowda@gmail.com", "🟤"
            ),
            FarmerProduct(
                "Foxtail Millet", "ನವಣೆ (Navane)",
                "Suresh Patil", "Gadag, Karnataka",
                55, 150, "Premium Quality",
                "9845678901", "suresh.patil@gmail.com", "🌾"
            ),
            FarmerProduct(
                "Pearl Millet", "ಸಜ್ಜೆ (Sajje)",
                "Manjunath Reddy", "Bellary, Karnataka",
                38, 300, "Farm Fresh",
                "9738291046", "manju.reddy@gmail.com", "🌿"
            ),
            FarmerProduct(
                "Sorghum", "ಬರಗು (Baragu)",
                "Venkatesh Kumar", "Raichur, Karnataka",
                32, 500, "Certified Organic",
                "9654321098", "venkatesh.k@gmail.com", "☀️"
            ),
            FarmerProduct(
                "Kodo Millet", "ಊದಲು (Oodalu)",
                "Basavraj Nayak", "Koppal, Karnataka",
                75, 80, "Rare Variety",
                "9512345678", "basavraj.n@gmail.com", "🌱"
            ),
            FarmerProduct(
                "Little Millet", "ಸಾಮೆ (Saame)",
                "Krishnappa Hegde", "Shivamogga, Karnataka",
                65, 120, "Traditional Variety",
                "9423456789", "krishna.hegde@gmail.com", "✨"
            )
        )

        recycler.adapter = DirectBuyAdapter(products)
    }
}