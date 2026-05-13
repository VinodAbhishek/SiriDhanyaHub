package com.vinodabhishek.siridhanyahub

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class HealthDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_detail)

        val name = intent.getStringExtra("millet_name") ?: ""
        val localName = intent.getStringExtra("millet_local") ?: ""
        val emoji = intent.getStringExtra("millet_emoji") ?: ""
        val benefit1 = intent.getStringExtra("benefit1") ?: ""
        val benefit2 = intent.getStringExtra("benefit2") ?: ""
        val benefit3 = intent.getStringExtra("benefit3") ?: ""
        val gi = intent.getStringExtra("glycemic_index") ?: ""
        val protein = intent.getFloatExtra("protein", 0f)
        val carbs = intent.getFloatExtra("carbs", 0f)
        val fiber = intent.getFloatExtra("fiber", 0f)
        val minerals = intent.getFloatExtra("minerals", 0f)

        findViewById<TextView>(R.id.tv_health_detail_emoji).text = emoji
        findViewById<TextView>(R.id.tv_health_detail_name).text = name
        findViewById<TextView>(R.id.tv_health_detail_local).text = localName
        findViewById<TextView>(R.id.tv_health_detail_b1).text = "✅ $benefit1"
        findViewById<TextView>(R.id.tv_health_detail_b2).text = "✅ $benefit2"
        findViewById<TextView>(R.id.tv_health_detail_b3).text = "✅ $benefit3"
        findViewById<TextView>(R.id.tv_health_detail_gi).text = "Glycemic Index: $gi"

        findViewById<Button>(R.id.btn_back).setOnClickListener {
            finish()
        }

        setupPieChart(protein, carbs, fiber, minerals)
    }

    private fun setupPieChart(protein: Float, carbs: Float, fiber: Float, minerals: Float) {
        val chart = findViewById<PieChart>(R.id.pie_chart)

        val entries = listOf(
            PieEntry(protein, "Protein"),
            PieEntry(carbs, "Carbs"),
            PieEntry(fiber, "Fiber"),
            PieEntry(minerals, "Minerals")
        )

        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(
                Color.parseColor("#2E7D32"),
                Color.parseColor("#F57F17"),
                Color.parseColor("#1565C0"),
                Color.parseColor("#6A1B9A")
            )
            valueTextSize = 13f
            valueTextColor = Color.WHITE
            sliceSpace = 3f
        }

        chart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 40f
            setHoleColor(Color.WHITE)
            setCenterText("Nutrition")
            setCenterTextSize(14f)
            legend.isEnabled = true
            legend.textSize = 12f
            animateY(800)
            invalidate()
        }
    }
}