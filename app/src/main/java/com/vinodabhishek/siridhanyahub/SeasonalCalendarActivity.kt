package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class SeasonalCalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seasonal_calendar)

        findViewById<Button>(R.id.btn_calendar_back).setOnClickListener { finish() }

        val month = Calendar.getInstance().get(Calendar.MONTH)
        val tip = when (month) {
            5, 6 -> "🌧️ June-July: Time to sow Ragi and Pearl Millet! Prepare your fields and ensure proper drainage for the monsoon season."
            7, 8 -> "🌱 August-September: Your Kharif millets are growing! Keep weeding and watch for pests. Ragi needs extra care now."
            9 -> "🌾 October: Harvest time for Kharif millets! Ragi and Pearl Millet should be ready. Store in dry, cool places."
            10, 11 -> "❄️ November-December: Sow Foxtail and Kodo Millet for the Rabi season. Soil preparation is key!"
            0, 1 -> "🌿 January-February: Rabi millets are growing well. Minimal irrigation needed. Monitor for aphids."
            2 -> "🌾 March: Harvest your Rabi millets! Foxtail and Kodo Millet should be golden and ready."
            3, 4 -> "☀️ April-May: Summer crop time! Sow heat-tolerant Pearl Millet and Sorghum. Water early morning only."
            else -> "🌾 It's always a good time to eat millets! Visit your local mandi for fresh stock."
        }

        findViewById<TextView>(R.id.tv_monthly_tip).text = tip
    }
}