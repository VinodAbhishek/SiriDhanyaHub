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
            Recipe("Ragi Mudde", "Finger Millet", "20 mins", "Easy", "180 kcal", "🟤",
                listOf("1 cup ragi flour", "2 cups water", "Salt to taste"),
                listOf("Boil 2 cups of water in a pan", "Add salt and slowly mix in ragi flour",
                    "Stir continuously on low flame for 5 minutes", "Shape into round balls while hot",
                    "Serve with sambar or chutney")),
            Recipe("Foxtail Millet Khichdi", "Navane", "25 mins", "Easy", "220 kcal", "🍲",
                listOf("1 cup foxtail millet", "1/2 cup moong dal", "1 onion chopped",
                    "1 tomato chopped", "Spices: cumin, turmeric, salt"),
                listOf("Wash and soak millet and dal for 15 mins", "Heat oil, add cumin seeds",
                    "Add onion and fry till golden", "Add tomato and spices",
                    "Add millet, dal and 3 cups water", "Pressure cook for 3 whistles")),
            Recipe("Pearl Millet Roti", "Sajje", "15 mins", "Medium", "150 kcal", "🫓",
                listOf("2 cups pearl millet flour", "Warm water as needed", "Salt to taste"),
                listOf("Mix flour and salt in a bowl", "Add warm water gradually and knead to soft dough",
                    "Divide into equal balls", "Roll out into thin rotis",
                    "Cook on hot tawa for 2 mins each side", "Serve with ghee or chutney")),
            Recipe("Sorghum Dosa", "Baragu", "30 mins", "Medium", "160 kcal", "🥞",
                listOf("2 cups sorghum flour", "1/2 cup rice flour", "Salt", "Water", "Oil"),
                listOf("Mix sorghum and rice flour with salt", "Add water to make thin batter",
                    "Rest batter for 20 minutes", "Heat dosa pan and grease lightly",
                    "Pour batter and spread in circular motion", "Cook till crispy, serve hot")),
            Recipe("Kodo Millet Pongal", "Oodalu", "35 mins", "Easy", "240 kcal", "🍛",
                listOf("1 cup kodo millet", "1/2 cup moong dal", "Pepper", "Cumin",
                    "Ghee", "Cashews", "Curry leaves"),
                listOf("Dry roast millet for 2 minutes", "Cook millet and dal together with 4 cups water",
                    "Heat ghee, add pepper and cumin", "Add cashews and curry leaves",
                    "Mix tempering into cooked millet", "Serve hot with coconut chutney")),
            Recipe("Ragi Porridge", "Finger Millet", "10 mins", "Easy", "130 kcal", "🥣",
                listOf("3 tbsp ragi flour", "2 cups milk or water", "2 tsp jaggery", "Cardamom"),
                listOf("Mix ragi flour in half cup cold water", "Boil remaining milk in a pan",
                    "Add ragi mixture slowly stirring continuously", "Cook for 5 mins on low flame",
                    "Add jaggery and cardamom", "Serve warm")),
            Recipe("Navane Upma", "Foxtail Millet", "20 mins", "Easy", "200 kcal", "🍽️",
                listOf("1 cup foxtail millet", "1 onion", "Green chillies", "Mustard seeds",
                    "Curry leaves", "Oil", "Salt"),
                listOf("Dry roast millet till light golden", "Heat oil, add mustard seeds",
                    "Add onion, chillies and curry leaves", "Add 2.5 cups water and salt",
                    "Add roasted millet and stir well", "Cover and cook for 10 minutes")),
            Recipe("Millet Ladoo", "Mixed Millet", "25 mins", "Easy", "210 kcal", "🟡",
                listOf("1 cup mixed millet flour", "1/2 cup jaggery powder", "3 tbsp ghee",
                    "Cardamom powder", "Cashews"),
                listOf("Dry roast millet flour till aromatic", "Mix in jaggery and cardamom",
                    "Add warm ghee and mix well", "Add cashews and combine",
                    "Shape into round ladoos while warm", "Store in airtight container"))
        )

        recycler.adapter = RecipeAdapter(recipes, requireContext())
    }
}