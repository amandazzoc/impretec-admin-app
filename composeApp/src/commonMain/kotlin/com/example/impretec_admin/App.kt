package com.example.impretec_admin

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.impretec_admin.network.ApiClient
import com.example.impretec_admin.state.OrderViewModel
import com.example.impretec_admin.ui.screens.OrderScreen

@Composable
fun App() {
    val viewModel = remember { OrderViewModel(ApiClient) }

    MaterialTheme {
        OrderScreen(viewModel)
    }
}