package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        findViewById<Button>(R.id.btn_calc_back).setOnClickListener { finish() }

        findViewById<Button>(R.id.btn_calculate).setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val weightStr = findViewById<EditText>(R.id.et_weight).text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.radio_goal)

        if (weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter your weight!", Toast.LENGTH_SHORT).show()
            return
        }
        if (radioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select a health goal!", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toFloat()

        val goal = when (radioGroup.checkedRadioButtonId) {
            R.id.radio_weightloss -> "weightloss"
            R.id.radio_diabetes -> "diabetes"
            R.id.radio_bone -> "bone"
            R.id.radio_energy -> "energy"
            R.id.radio_digestion -> "digestion"
            else -> "energy"
        }

        val (millet, intakeGrams, nutrition, tip) = getRecommendation(goal, weight)

        val cardResult = findViewById<CardView>(R.id.card_result)
        cardResult.visibility = View.VISIBLE

        findViewById<TextView>(R.id.tv_result_title).text = "✅ Your Personalized Plan"
        findViewById<TextView>(R.id.tv_result_millet).text = "🌾 Recommended Millet: $millet"
        findViewById<TextView>(R.id.tv_result_intake).text = "📦 Daily Intake: ${intakeGrams}g per day"
        findViewById<TextView>(R.id.tv_result_nutrition).text = nutrition
        findViewById<TextView>(R.id.tv_result_tip).text = "💡 $tip"
    }

    private fun getRecommendation(goal: String, weight: Float): List<String> {
        val intake = (weight * 1.5f).toInt().toString()
        return when (goal) {
            "weightloss" -> listOf(
                "Foxtail Millet (Navane)",
                intake,
                "🔥 Calories: ~${(intake.toInt() * 1.4).toInt()} kcal\n💪 Protein: ${(intake.toInt() * 0.12).toInt()}g\n🌿 Fiber: ${(intake.toInt() * 0.08).toInt()}g",
                "Foxtail Millet is low in calories and high in fiber — keeps you full longer and reduces cravings. Have it as khichdi or upma for breakfast!"
            )
            "diabetes" -> listOf(
                "Kodo Millet (Oodalu)",
                intake,
                "📊 Glycemic Index: Very Low (52)\n💪 Protein: ${(intake.toInt() * 0.09).toInt()}g\n🌿 Fiber: ${(intake.toInt() * 0.09).toInt()}g",
                "Kodo Millet has a very low glycemic index — ideal for managing blood sugar levels. Replace your white rice with Kodo Millet for best results!"
            )
            "bone" -> listOf(
                "Finger Millet (Ragi)",
                intake,
                "🦴 Calcium: ${(intake.toInt() * 3.44).toInt()}mg\n💪 Protein: ${(intake.toInt() * 0.073).toInt()}g\n⚡ Iron: ${(intake.toInt() * 0.038).toInt()}mg",
                "Finger Millet has more calcium than milk! Perfect for bone strength, especially for women and children. Have it as Ragi Mudde or Ragi porridge daily!"
            )
            "energy" -> listOf(
                "Pearl Millet (Sajje)",
                intake,
                "⚡ Calories: ~${(intake.toInt() * 3.78).toInt()} kcal\n💪 Protein: ${(intake.toInt() * 0.11).toInt()}g\n🔩 Iron: ${(intake.toInt() * 0.08).toInt()}mg",
                "Pearl Millet is rich in iron and protein — great for energy and fighting fatigue. Have it as roti or porridge before workouts!"
            )
            "digestion" -> listOf(
                "Sorghum (Baragu)",
                intake,
                "🌿 Fiber: ${(intake.toInt() * 0.062).toInt()}g\n💪 Protein: ${(intake.toInt() * 0.104).toInt()}g\n🔥 Calories: ~${(intake.toInt() * 3.29).toInt()} kcal",
                "Sorghum is gluten-free and rich in fiber — excellent for gut health and digestion. Have it as dosa or roti for a healthy digestive system!"
            )
            else -> listOf("Finger Millet (Ragi)", intake, "", "")
        }
    }
}