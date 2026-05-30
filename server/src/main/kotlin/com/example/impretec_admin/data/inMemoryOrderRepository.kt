package com.example.impretec_admin.data

import com.example.impretec_admin.model.Order

class inMemoryOrderRepository: OrderRepository {
    private val orders = mutableListOf<Order>()
    private var idCounter = 0

    override fun getAll(): List<Order> = orders.toList()

    override fun add(order: Order): Order {
        val newOrder = order.copy(orderId = idCounter++)
        orders.add(newOrder)
        return newOrder
    }

    override fun update(orderId: Int, order: Order) {
        val index = orders.indexOfFirst { orderId == order.orderId }

        if (index != -1) {
            orders[index] = order
        } else {
            println("Pedido não encontrado.")
        }
    }

    override fun delete(orderId: Int) {
        orders.removeIf { it.orderId == orderId }
    }
}