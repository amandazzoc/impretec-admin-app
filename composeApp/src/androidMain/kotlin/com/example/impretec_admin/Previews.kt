package com.example.impretec_admin

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import com.example.impretec_admin.ui.components.OrderItem
import com.example.impretec_admin.model.Order

@Composable
fun PreviewOrderItem() {
    MaterialTheme {
        OrderItem(
            order = Order(1, "Chaveiro", 10, 10.99,"Amanda"),
            onDelete = {}
        )
    }
}