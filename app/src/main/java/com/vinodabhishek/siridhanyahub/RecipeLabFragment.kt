package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeLabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_lab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_recipes)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val recipes = listOf(
            Recipe("Ragi Mudde", "Finger Millet", "20 mins", "Easy", "180 kcal", "🟤"),
            Recipe("Foxtail Millet Khichdi", "Navane", "25 mins", "Easy", "220 kcal", "🍲"),
            Recipe("Pearl Millet Roti", "Sajje", "15 mins", "Medium", "150 kcal", "🫓"),
            Recipe("Sorghum Dosa", "Baragu", "30 mins", "Medium", "160 kcal", "🥞"),
            Recipe("Kodo Millet Pongal", "Oodalu", "35 mins", "Easy", "240 kcal", "🍛"),
            Recipe("Ragi Porridge", "Finger Millet", "10 mins", "Easy", "130 kcal", "🥣"),
            Recipe("Navane Upma", "Foxtail Millet", "20 mins", "Easy", "200 kcal", "🍽️"),
            Recipe("Sajje Idli", "Pearl Millet", "40 mins", "Hard", "120 kcal", "🍱"),
            Recipe("Baragu Pulao", "Sorghum", "30 mins", "Medium", "280 kcal", "🍚"),
            Recipe("Millet Ladoo", "Mixed Millet", "25 mins", "Easy", "210 kcal", "🟡")
        )

        recycler.adapter = RecipeAdapter(recipes)
    }
}