package com.vinodabhishek.siridhanyahub

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        QuizQuestion(
            "Which millet is also known as 'Ragi' in Karnataka?",
            listOf("Pearl Millet", "Finger Millet", "Foxtail Millet", "Kodo Millet"),
            1, "Ragi (Finger Millet) is a powerhouse of calcium — 100g has more calcium than milk!"
        ),
        QuizQuestion(
            "Which millet has the lowest Glycemic Index?",
            listOf("Sorghum", "Pearl Millet", "Foxtail Millet", "Finger Millet"),
            2, "Foxtail Millet (Navane) has a very low GI of around 50, making it great for diabetics!"
        ),
        QuizQuestion(
            "What is the local Kannada name for Pearl Millet?",
            listOf("Navane", "Sajje", "Baragu", "Oodalu"),
            1, "Sajje (Pearl Millet) is one of the most drought-resistant crops in the world!"
        ),
        QuizQuestion(
            "Which millet is known as 'Siri Dhanya' meaning auspicious grain?",
            listOf("Only Ragi", "Only Foxtail", "All small millets", "Only Kodo"),
            2, "All small millets are called 'Siri Dhanya' — the auspicious grains of India!"
        ),
        QuizQuestion(
            "Ragi Mudde is a traditional dish from which state?",
            listOf("Tamil Nadu", "Maharashtra", "Karnataka", "Andhra Pradesh"),
            2, "Ragi Mudde is the staple food of Karnataka, especially in rural areas!"
        ),
        QuizQuestion(
            "Which millet is richest in protein among these?",
            listOf("Finger Millet", "Kodo Millet", "Foxtail Millet", "Pearl Millet"),
            3, "Pearl Millet (Sajje) has the highest protein content among common millets at ~11g per 100g!"
        ),
        QuizQuestion(
            "What is 'Navane' in English?",
            listOf("Kodo Millet", "Barnyard Millet", "Foxtail Millet", "Little Millet"),
            2, "Navane is Foxtail Millet — named for its fox-tail shaped seed head!"
        ),
        QuizQuestion(
            "Which millet is best for bone health due to high calcium?",
            listOf("Pearl Millet", "Sorghum", "Finger Millet", "Foxtail Millet"),
            2, "Finger Millet has 344mg calcium per 100g — the highest among all cereals!"
        )
    )

    private var currentIndex = 0
    private var score = 0
    private var answered = false

    private lateinit var tvQuestion: TextView
    private lateinit var tvProgress: TextView
    private lateinit var tvFunFact: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var optionsLayout: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var funFactCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestion = findViewById(R.id.tv_question)
        tvProgress = findViewById(R.id.tv_progress)
        tvFunFact = findViewById(R.id.tv_fun_fact)
        progressBar = findViewById(R.id.progress_quiz)
        optionsLayout = findViewById(R.id.options_layout)
        btnNext = findViewById(R.id.btn_next)
        funFactCard = findViewById(R.id.card_fun_fact)

        findViewById<Button>(R.id.btn_quiz_back).setOnClickListener { finish() }

        loadQuestion()

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                showResult()
            }
        }
    }

    private fun loadQuestion() {
        answered = false
        funFactCard.visibility = android.view.View.GONE
        btnNext.visibility = android.view.View.GONE

        val q = questions[currentIndex]
        tvQuestion.text = q.question
        tvProgress.text = "Question ${currentIndex + 1} of ${questions.size}  |  Score: $score"
        progressBar.progress = ((currentIndex + 1) * 100) / questions.size

        optionsLayout.removeAllViews()
        q.options.forEachIndexed { index, option ->
            val btn = Button(this).apply {
                text = option
                textSize = 14f
                setTextColor(Color.parseColor("#1B5E20"))
                setBackgroundColor(Color.parseColor("#F1F8E9"))
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                layoutParams = params
                setPadding(24, 24, 24, 24)
                setOnClickListener { onOptionSelected(index, this) }
            }
            optionsLayout.addView(btn)
        }
    }

    private fun onOptionSelected(selectedIndex: Int, selectedBtn: Button) {
        if (answered) return
        answered = true

        val q = questions[currentIndex]

        for (i in 0 until optionsLayout.childCount) {
            val btn = optionsLayout.getChildAt(i) as Button
            btn.isEnabled = false
            if (i == q.correctIndex) {
                btn.setBackgroundColor(Color.parseColor("#2E7D32"))
                btn.setTextColor(Color.WHITE)
            } else if (i == selectedIndex) {
                btn.setBackgroundColor(Color.parseColor("#C62828"))
                btn.setTextColor(Color.WHITE)
            }
        }

        if (selectedIndex == q.correctIndex) score++

        tvFunFact.text = "💡 ${q.funFact}"
        funFactCard.visibility = android.view.View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            btnNext.text = if (currentIndex + 1 < questions.size) "Next Question →" else "See Result 🏆"
            btnNext.visibility = android.view.View.VISIBLE
        }, 800)
    }

    private fun showResult() {
        setContentView(R.layout.activity_quiz_result)

        val emoji = when {
            score >= 7 -> "🏆"
            score >= 5 -> "🌟"
            score >= 3 -> "👍"
            else -> "📚"
        }
        val message = when {
            score >= 7 -> "Millet Master! You know your Siri Dhanya!"
            score >= 5 -> "Great job! You know millets well!"
            score >= 3 -> "Good effort! Keep learning about millets!"
            else -> "Keep exploring the world of millets!"
        }

        findViewById<TextView>(R.id.tv_result_emoji).text = emoji
        findViewById<TextView>(R.id.tv_result_score).text = "$score / ${questions.size}"
        findViewById<TextView>(R.id.tv_result_message).text = message

        findViewById<Button>(R.id.btn_retake).setOnClickListener {
            currentIndex = 0
            score = 0
            setContentView(R.layout.activity_quiz)
            tvQuestion = findViewById(R.id.tv_question)
            tvProgress = findViewById(R.id.tv_progress)
            tvFunFact = findViewById(R.id.tv_fun_fact)
            progressBar = findViewById(R.id.progress_quiz)
            optionsLayout = findViewById(R.id.options_layout)
            btnNext = findViewById(R.id.btn_next)
            funFactCard = findViewById(R.id.card_fun_fact)
            findViewById<Button>(R.id.btn_quiz_back).setOnClickListener { finish() }
            btnNext.setOnClickListener {
                currentIndex++
                if (currentIndex < questions.size) loadQuestion() else showResult()
            }
            loadQuestion()
        }

        findViewById<Button>(R.id.btn_result_back).setOnClickListener { finish() }
    }
}