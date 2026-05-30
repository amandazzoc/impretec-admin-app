package com.example.impretec_admin

import com.example.impretec_admin.routes.orderRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val orderRepository = com.example.impretec_admin.data.inMemoryOrderRepository()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        orderRoutes(orderRepository)
    }
}