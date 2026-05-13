package com.vinodabhishek.siridhanyahub

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MandiWatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mandi_watch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChart(view)

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

        recycler.adapter = MandiAdapter(priceData)
    }

    private fun setupChart(view: View) {
        val chart = view.findViewById<LineChart>(R.id.price_chart)

        // Ragi weekly prices (Mon-Sun)
        val ragiEntries = listOf(
            Entry(0f, 3100f), Entry(1f, 3150f), Entry(2f, 3200f),
            Entry(3f, 3180f), Entry(4f, 3250f), Entry(5f, 3300f), Entry(6f, 3400f)
        )

        // Navane weekly prices
        val navaneEntries = listOf(
            Entry(0f, 2600f), Entry(1f, 2650f), Entry(2f, 2700f),
            Entry(3f, 2750f), Entry(4f, 2800f), Entry(5f, 2900f), Entry(6f, 2950f)
        )

        // Sajje weekly prices
        val sajjeEntries = listOf(
            Entry(0f, 2400f), Entry(1f, 2350f), Entry(2f, 2300f),
            Entry(3f, 2280f), Entry(4f, 2250f), Entry(5f, 2200f), Entry(6f, 2200f)
        )

        val ragiSet = LineDataSet(ragiEntries, "Ragi").apply {
            color = Color.parseColor("#2E7D32")
            setCircleColor(Color.parseColor("#2E7D32"))
            lineWidth = 2.5f
            circleRadius = 4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        val navaneSet = LineDataSet(navaneEntries, "Navane").apply {
            color = Color.parseColor("#F57F17")
            setCircleColor(Color.parseColor("#F57F17"))
            lineWidth = 2.5f
            circleRadius = 4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        val sajjeSet = LineDataSet(sajjeEntries, "Sajje").apply {
            color = Color.parseColor("#1565C0")
            setCircleColor(Color.parseColor("#1565C0"))
            lineWidth = 2.5f
            circleRadius = 4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        chart.apply {
            data = LineData(ragiSet, navaneSet, sajjeSet)
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
                textSize = 12f
            }
            animateX(800)
        }
    }
}