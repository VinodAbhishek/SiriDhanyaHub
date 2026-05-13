package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val name = intent.getStringExtra("recipe_name") ?: ""
        val millet = intent.getStringExtra("recipe_millet") ?: ""
        val time = intent.getStringExtra("recipe_time") ?: ""
        val difficulty = intent.getStringExtra("recipe_difficulty") ?: ""
        val calories = intent.getStringExtra("recipe_calories") ?: ""
        val emoji = intent.getStringExtra("recipe_emoji") ?: ""
        val ingredients = intent.getStringArrayListExtra("recipe_ingredients") ?: arrayListOf()
        val steps = intent.getStringArrayListExtra("recipe_steps") ?: arrayListOf()

        findViewById<TextView>(R.id.tv_detail_emoji).text = emoji
        findViewById<TextView>(R.id.tv_detail_name).text = name
        findViewById<TextView>(R.id.tv_detail_millet).text = "🌾 $millet"
        findViewById<TextView>(R.id.tv_detail_time).text = "⏱ $time"
        findViewById<TextView>(R.id.tv_detail_difficulty).text = "📊 $difficulty"
        findViewById<TextView>(R.id.tv_detail_calories).text = "🔥 $calories"

        val ingredientsText = ingredients.joinToString("\n") { "• $it" }
        findViewById<TextView>(R.id.tv_detail_ingredients).text = ingredientsText

        val stepsText = steps.mapIndexed { i, s -> "${i + 1}. $s" }.joinToString("\n\n")
        findViewById<TextView>(R.id.tv_detail_steps).text = stepsText

        findViewById<Button>(R.id.btn_recipe_back).setOnClickListener {
            finish()
        }
    }
}