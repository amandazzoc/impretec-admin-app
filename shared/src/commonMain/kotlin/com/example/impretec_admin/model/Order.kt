package com.example.impretec_admin.model

import kotlinx.serialization.Serializable

@Serializable
data class Order (
    val orderId: Int = 0,
    val productName: String,
    val productAmount: Int,
    val productPrice: Double,
    val client: String,
)