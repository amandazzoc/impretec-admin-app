package com.example.impretec_admin.routes

import com.example.impretec_admin.data.OrderRepository
import com.example.impretec_admin.model.Order
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put

fun Route.orderRoutes(orderRepository: OrderRepository) {
    get("/orders") {
        val orders = orderRepository.getAll()
        call.respond(orders)
    }

    post("/orders") {
        val orderRequest = call.receive<Order>()

        try {
            val createdOrder = orderRepository.add(orderRequest)
            call.respond(HttpStatusCode.Created, createdOrder)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest,"Formato de curso inválido")
        }
    }

    put("orders/{orderId}") {
        val orderId = call.parameters["orderId"]?.toIntOrNull()
            ?:return@put call.respond(HttpStatusCode.NotFound, "Pedido não encontrado")

        val orderRequest = call.receive<Order>()

        try {
            val updatedOrder = orderRepository.update(orderId, orderRequest)
            call.respond(HttpStatusCode.OK, "Pedido $updatedOrder atualizado com sucesso!")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao atualizar o order $orderId")
        }
    }

    delete("/orders/{orderId}") {
        val orderId = call.parameters["orderId"]?.toIntOrNull()
            ?:return@delete call.respond(HttpStatusCode.NotFound, "Pedido não encontrado")

        try {
            orderRepository.delete(orderId)
            call.respond(HttpStatusCode.NoContent)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao deletar o order $orderId")
        }
    }
}