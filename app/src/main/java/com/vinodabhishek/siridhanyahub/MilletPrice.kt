package com.vinodabhishek.siridhanyahub

data class MilletPrice(
    val milletName: String,
    val city: String,
    val currentPrice: Int,
    val highPrice: Int,
    val lowPrice: Int,
    val trend: String
)