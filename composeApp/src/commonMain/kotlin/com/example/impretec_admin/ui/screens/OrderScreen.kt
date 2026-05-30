package com.example.impretec_admin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.impretec_admin.state.OrderViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.impretec_admin.ui.components.OrderItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // Estou ciente de que estou usando uma API que ainda pode sofrer alterações
@Composable
fun OrderScreen(viewModel: OrderViewModel) {
    val scope = rememberCoroutineScope()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.loadOrders()
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Seção do form
        OutlinedTextField(
            value = state.inputProductName,
            onValueChange = { viewModel.onProductNameChanged(it) },
            label = { Text("Nome do produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer( modifier = Modifier.height(8.dp) )

        OutlinedTextField(
            value = state.inputClient,
            onValueChange = { viewModel.onClientChanged(it) },
            label = { Text("Nome do cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer( modifier = Modifier.height(8.dp) )

        OutlinedTextField(
            value = state.inputProductAmount.toString(),
            onValueChange = { viewModel.onProductAmountChanged(it.toInt()) },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer( modifier = Modifier.height(8.dp) )

        OutlinedTextField(
            value = state.inputProductPrice.toString(),
            onValueChange = { viewModel.onProductPriceChanged(it.toDouble()) },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer( modifier = Modifier.height(8.dp) )

        Button(
            onClick = { scope.launch { viewModel.saveOrder() } },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text("Salvar pedido")
        }

        Spacer( modifier = Modifier.height(16.dp))
        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        Spacer( modifier = Modifier.height(16.dp))

        if(state.isLoading){
            CircularProgressIndicator()
        } else if (state.errorMessage != null){
            Text("Oops, aconteceu um erro: ${state.errorMessage}")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ){
                items(state.orders) { order ->
                    OrderItem(
                        order = order,
                        onDelete = {
                            scope.launch { viewModel.deleteOrder(order.orderId) }
                        }
                    )
                }
            }
        }
    }
}