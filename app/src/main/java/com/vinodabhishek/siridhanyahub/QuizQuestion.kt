package com.vinodabhishek.siridhanyahub

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val funFact: String
)