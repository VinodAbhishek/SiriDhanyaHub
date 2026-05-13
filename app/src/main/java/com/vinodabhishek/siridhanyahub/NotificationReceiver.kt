package com.vinodabhishek.siridhanyahub

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val tips = listOf(
            "🌾 Start your day with Ragi porridge — packed with calcium and energy!",
            "🟡 Foxtail Millet (Navane) has a very low glycemic index — great for diabetics!",
            "💪 Pearl Millet (Sajje) is rich in iron — fight fatigue naturally!",
            "🌿 Sorghum (Baragu) is gluten-free — safe and healthy for everyone!",
            "🩸 Kodo Millet (Oodalu) detoxifies your body and controls blood sugar!",
            "🦴 100g of Ragi has more calcium than a glass of milk — eat Ragi daily!",
            "🍽️ Replace white rice with millets — better nutrition, better health!",
            "🌱 Millets need 70% less water than rice — eating millets saves the planet!",
            "⚡ Foxtail Millet Upma is a perfect energy-boosting breakfast!",
            "🏆 Millets are called Siri Dhanya — the auspicious grains of India!"
        )

        val randomTip = tips.random()
        val channelId = "millet_tips_channel"

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Millet Tips",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily millet health tips from SiriDhanyaHub"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val mainIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("🌾 Daily Millet Tip")
            .setContentText(randomTip)
            .setStyle(NotificationCompat.BigTextStyle().bigText(randomTip))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1001, notification)
    }
}