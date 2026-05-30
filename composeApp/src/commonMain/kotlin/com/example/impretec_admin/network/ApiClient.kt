package com.example.impretec_admin.network

import com.example.impretec_admin.BASE_URL
import com.example.impretec_admin.model.Order
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
object ApiClient {
    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = false
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }
    }

    suspend fun getOrders(): List<Order> {
        return httpClient.get("$BASE_URL/orders").body()
    }

    suspend fun createOrder(order: Order): Order {
        return httpClient.post("$BASE_URL/orders") {
            contentType(ContentType.Application.Json)
            setBody(order)
        }.body<Order>()
    }

    suspend fun updateOrder(orderId:Int, order: Order): Order {
        return httpClient.put("$BASE_URL/orders/$orderId") {
            contentType(ContentType.Application.Json)
            setBody(order.copy(orderId = orderId))
        }.body<Order>()
    }

    suspend fun deleteOrder(orderId:Int) {
        httpClient.delete("$BASE_URL/orders/$orderId")
    }
}