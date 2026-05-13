package com.vinodabhishek.siridhanyahub

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        findViewById<Button>(R.id.btn_about_back).setOnClickListener { finish() }

        val prefs = getSharedPreferences("siri_prefs", MODE_PRIVATE)
        val notifSwitch = findViewById<SwitchCompat>(R.id.switch_notifications)

        notifSwitch.isChecked = prefs.getBoolean("notifications_enabled", true)

        notifSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notifications_enabled", isChecked).apply()
            if (isChecked) {
                NotificationScheduler.scheduleDailyNotification(this)
            } else {
                NotificationScheduler.cancelNotification(this)
            }
        }
    }
}