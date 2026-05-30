package com.example.impretec_admin.data

import com.example.impretec_admin.model.Order

interface OrderRepository {
    fun getAll(): List<Order>
    fun add(order: Order) : Order
    fun update(orderId: Int, order: Order)
    fun delete(orderId: Int)
}