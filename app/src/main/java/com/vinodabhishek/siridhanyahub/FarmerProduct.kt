package com.vinodabhishek.siridhanyahub

data class FarmerProduct(
    val milletName: String,
    val localName: String,
    val farmerName: String,
    val location: String,
    val pricePerKg: Int,
    val quantityKg: Int,
    val quality: String,
    val phone: String,
    val email: String,
    val emoji: String
)