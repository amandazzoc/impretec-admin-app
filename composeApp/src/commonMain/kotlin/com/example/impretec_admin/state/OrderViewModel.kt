package com.example.impretec_admin.state

import com.example.impretec_admin.model.Order
import com.example.impretec_admin.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel(private val api: ApiClient) {
    private val _uiState = MutableStateFlow(OrderUiState())

    val uiState= _uiState.asStateFlow()

    fun onProductNameChanged(productName: String) {
        _uiState.update { it.copy(inputProductName = productName) }
    }

    fun onProductAmountChanged(productAmount: Int) {
        _uiState.update { it.copy(inputProductAmount = productAmount) }
    }

    fun onProductPriceChanged(productPrice: Double) {
        _uiState.update { it.copy(inputProductPrice = productPrice) }
    }

    fun onClientChanged(client: String) {
        _uiState.update { it.copy(inputClient = client) }
    }

    // comunicacao com a API
    suspend fun loadOrders() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        try {
            val orders = api.getOrders()
            _uiState.update { it.copy(isLoading = false, orders = orders) }
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Erro ao carregar os pedidos: ${e.message}") }
        }
    }

    suspend fun saveOrder() {
        val state = _uiState.value

        if(state.inputProductName.isBlank() || state.inputClient.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Preencha os campos obrigatórios") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        try {
            val newOrder = Order(
                productName = state.inputProductName,
                productAmount = state.inputProductAmount ?: 1,
                productPrice = state.inputProductPrice,
                client = state.inputClient
            )

            api.createOrder(newOrder)

            _uiState.update {
                it.copy(
                    errorMessage = null,
                    inputProductName = "",
                    inputClient = "",
                    inputProductAmount = 0,
                    inputProductPrice = 0.0
                )
            }

            loadOrders()
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Erro ao salvar: ${e.message}") }
        }
    }

    suspend fun deleteOrder(orderId: Int) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        try {
            api.deleteOrder(orderId)

            loadOrders()
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Erro ao deletar: ${e.message}") }
        }
    }

    suspend fun updateOrder(orderId: Int) {
        val state = _uiState.value

        if(state.inputProductName.isBlank() || state.inputClient.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Preencha os campos obrigatórios") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        try {
            val updatedOrder = Order(
                productName = state.inputProductName,
                productAmount = state.inputProductAmount ?: 1,
                productPrice = state.inputProductPrice,
                client = state.inputClient
            )

            api.updateOrder(orderId, updatedOrder)

            _uiState.update {
                it.copy(
                    errorMessage = null,
                    inputProductName = "",
                    inputClient = "",
                    inputProductAmount = 0,
                    inputProductPrice = 0.0
                )
            }

            loadOrders()
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = "Erro ao atualizar o pedido: ${e.message}"
                )
            }
        }
    }
}