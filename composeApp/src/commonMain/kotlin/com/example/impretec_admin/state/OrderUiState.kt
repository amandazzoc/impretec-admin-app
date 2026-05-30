package com.example.impretec_admin.state

import com.example.impretec_admin.model.Order

data class OrderUiState (
    val isLoading: Boolean = false,
    val orders: List<Order> = listOf(),
    val errorMessage: String? = null,

    val inputProductName: String = "",
    val inputProductAmount: Int = 0,
    val inputProductPrice: Double = 0.0,
    val inputClient: String = "",
)