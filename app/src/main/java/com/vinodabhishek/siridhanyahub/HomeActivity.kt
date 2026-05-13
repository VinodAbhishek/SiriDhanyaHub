package com.vinodabhishek.siridhanyahub

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<LinearLayout>(R.id.btn_ai_chat).setOnClickListener {
            startActivity(Intent(this, AiChatActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_mandi).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_recipe).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tab", "recipe")
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.btn_health).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tab", "health")
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.btn_directbuy).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tab", "directbuy")
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.btn_quiz).setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_calculator).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_calendar).setOnClickListener {
            startActivity(Intent(this, SeasonalCalendarActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_about).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}