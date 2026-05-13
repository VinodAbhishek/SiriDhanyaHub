package com.vinodabhishek.siridhanyahub

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MilletDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_millet_detail)

        val name = intent.getStringExtra("millet_name") ?: ""
        val city = intent.getStringExtra("millet_city") ?: ""
        val price = intent.getIntExtra("millet_price", 0)
        val high = intent.getIntExtra("millet_high", 0)
        val low = intent.getIntExtra("millet_low", 0)
        val trend = intent.getStringExtra("millet_trend") ?: ""

        findViewById<TextView>(R.id.tv_md_name).text = name
        findViewById<TextView>(R.id.tv_md_city).text = "📍 $city"
        findViewById<TextView>(R.id.tv_md_price).text = "₹$price/quintal"
        findViewById<TextView>(R.id.tv_md_high).text = "High: ₹$high"
        findViewById<TextView>(R.id.tv_md_low).text = "Low: ₹$low"
        findViewById<TextView>(R.id.tv_md_trend).apply {
            text = trend
            setTextColor(if (trend == "↑") Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
        }

        setupPriceChart(name, price)
        setupDemandChart(name)

        findViewById<Button>(R.id.btn_millet_back).setOnClickListener { finish() }
    }

    private fun setupPriceChart(name: String, currentPrice: Int) {
        val chart = findViewById<LineChart>(R.id.chart_price)

        val priceMap = mapOf(
            "Navane (Foxtail)" to listOf(2600f, 2650f, 2700f, 2750f, 2800f, 2900f, 2950f),
            "Sajje (Pearl)"    to listOf(2400f, 2350f, 2300f, 2280f, 2250f, 2200f, 2200f),
            "Baragu (Sorghum)" to listOf(1900f, 1920f, 1880f, 1950f, 1900f, 1870f, 1900f),
            "Ragi (Finger)"    to listOf(3100f, 3150f, 3200f, 3180f, 3250f, 3300f, 3400f),
            "Oodalu (Kodo)"    to listOf(4500f, 4520f, 4480f, 4600f, 4650f, 4700f, 4700f)
        )

        val prices = priceMap.entries.firstOrNull { name.contains(it.key.split(" ")[0]) }?.value
            ?: listOf(currentPrice.toFloat(), currentPrice.toFloat(), currentPrice.toFloat(),
                currentPrice.toFloat(), currentPrice.toFloat(), currentPrice.toFloat(), currentPrice.toFloat())

        val entries = prices.mapIndexed { i, v -> Entry(i.toFloat(), v) }
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        val dataSet = LineDataSet(entries, "Price (₹/quintal)").apply {
            color = Color.parseColor("#2E7D32")
            setCircleColor(Color.parseColor("#2E7D32"))
            lineWidth = 2.5f
            circleRadius = 4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            fillAlpha = 50
            setDrawFilled(true)
            fillColor = Color.parseColor("#A5D6A7")
        }

        chart.apply {
            data = LineData(dataSet)
            description.isEnabled = false
            setTouchEnabled(true)
            setPinchZoom(true)
            setBackgroundColor(Color.WHITE)
            xAxis.apply {
                granularity = 1f
                setDrawGridLines(false)
                labelCount = 7
            }
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = Color.parseColor("#E0E0E0")
            }
            axisRight.isEnabled = false
            legend.apply {
                isEnabled = true
                form = Legend.LegendForm.LINE
            }
            animateX(800)
        }
    }

    private fun setupDemandChart(name: String) {
        val chart = findViewById<LineChart>(R.id.chart_demand)

        val demandMap = mapOf(
            "Navane" to listOf(60f, 65f, 70f, 72f, 78f, 80f, 85f),
            "Sajje"  to listOf(80f, 75f, 70f, 68f, 65f, 62f, 60f),
            "Baragu" to listOf(50f, 52f, 48f, 55f, 53f, 50f, 52f),
            "Ragi"   to listOf(70f, 72f, 75f, 78f, 80f, 85f, 90f),
            "Oodalu" to listOf(40f, 42f, 45f, 43f, 48f, 50f, 52f)
        )

        val demand = demandMap.entries.firstOrNull { name.contains(it.key.split(" ")[0]) }?.value
            ?: listOf(50f, 52f, 54f, 56f, 58f, 60f, 62f)

        val entries = demand.mapIndexed { i, v -> Entry(i.toFloat(), v) }

        val dataSet = LineDataSet(entries, "Demand Index").apply {
            color = Color.parseColor("#F57F17")
            setCircleColor(Color.parseColor("#F57F17"))
            lineWidth = 2.5f
            circleRadius = 4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            fillAlpha = 50
            setDrawFilled(true)
            fillColor = Color.parseColor("#FFE082")
        }

        chart.apply {
            data = LineData(dataSet)
            description.isEnabled = false
            setTouchEnabled(true)
            setPinchZoom(true)
            setBackgroundColor(Color.WHITE)
            xAxis.apply {
                granularity = 1f
                setDrawGridLines(false)
                labelCount = 7
            }
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = Color.parseColor("#E0E0E0")
            }
            axisRight.isEnabled = false
            legend.apply {
                isEnabled = true
                form = Legend.LegendForm.LINE
            }
            animateX(1000)
        }
    }
}