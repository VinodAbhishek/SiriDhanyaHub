package com.vinodabhishek.siridhanyahub

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class AiChatActivity : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var scrollView: ScrollView
    private lateinit var inputMessage: EditText
    private val apiKey = "AIzaSyDwsarbAsF4kjBMQs7D3vNPCHa-oxVuq4g"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)

        chatContainer = findViewById(R.id.chat_container)
        scrollView = findViewById(R.id.scroll_chat)
        inputMessage = findViewById(R.id.input_message)

        findViewById<TextView>(R.id.btn_back).setOnClickListener { finish() }

        findViewById<LinearLayout>(R.id.btn_send).setOnClickListener {
            val msg = inputMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                addMessage(msg, isUser = true)
                inputMessage.setText("")
                callGeminiApi(msg)
            }
        }

        addMessage("👋 Hello! I'm your AI Millet Assistant. Ask me anything about siri dhanya millets — nutrition, recipes, health benefits, farming tips, and more!", isUser = false)
    }

    private fun addMessage(text: String, isUser: Boolean) {
        val tv = TextView(this)
        tv.text = text
        tv.textSize = 14f
        tv.setPadding(24, 16, 24, 16)

        val screenWidth = resources.displayMetrics.widthPixels
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = 8
        params.width = (screenWidth * 0.78).toInt()

        if (isUser) {
            tv.setBackgroundColor(Color.parseColor("#2E7D32"))
            tv.setTextColor(Color.WHITE)
            params.gravity = Gravity.END
            params.leftMargin = 80
        } else {
            tv.setBackgroundColor(Color.WHITE)
            tv.setTextColor(Color.parseColor("#1B5E20"))
            params.gravity = Gravity.START
            params.rightMargin = 80
            tv.setTypeface(null, Typeface.NORMAL)
        }

        tv.layoutParams = params
        chatContainer.addView(tv)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    private fun callGeminiApi(userMessage: String) {
        addMessage("⏳ Thinking...", isUser = false)

        Thread {
            try {
                val urlStr = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=$apiKey"
                val url = URL(urlStr)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "application/json")
                conn.doOutput = true
                conn.connectTimeout = 15000
                conn.readTimeout = 15000

                val systemContext = "You are an expert on Siri Dhanya millets (foxtail, kodo, barnyard, browntop, little millet). Answer questions about their nutrition, health benefits, recipes, farming, and market prices. Keep answers concise and helpful. Use emojis to make responses friendly."

                val part = JSONObject().put("text", "$systemContext\n\nUser question: $userMessage")
                val parts = JSONArray().put(part)
                val content = JSONObject().put("parts", parts)
                val contents = JSONArray().put(content)
                val body = JSONObject().put("contents", contents)

                conn.outputStream.use { it.write(body.toString().toByteArray()) }

                val responseCode = conn.responseCode

                if (responseCode == 200) {
                    val response = BufferedReader(InputStreamReader(conn.inputStream)).use { it.readText() }
                    val reply = JSONObject(response)
                        .getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")

                    runOnUiThread {
                        chatContainer.removeViewAt(chatContainer.childCount - 1)
                        addMessage(reply, isUser = false)
                    }
                } else {
                    runOnUiThread {
                        chatContainer.removeViewAt(chatContainer.childCount - 1)
                        val friendlyMessage = when (responseCode) {
                            429 -> "⚠️ Too many requests. Please wait a moment and try again!"
                            401, 403 -> "🔑 API key issue. Please check your Gemini API key."
                            400 -> "❌ Bad request. Please try a different question."
                            else -> "❌ Error $responseCode. Please try again."
                        }
                        addMessage(friendlyMessage, isUser = false)
                    }
                }

            } catch (e: Exception) {
                runOnUiThread {
                    chatContainer.removeViewAt(chatContainer.childCount - 1)
                    addMessage("❌ ${e.javaClass.simpleName}: ${e.message}", isUser = false)
                }
            }
        }.start()
    }
}