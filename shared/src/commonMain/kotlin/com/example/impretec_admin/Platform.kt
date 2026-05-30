package com.example.impretec_admin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
