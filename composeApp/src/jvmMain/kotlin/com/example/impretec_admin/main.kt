package com.example.impretec_admin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.impretec_admin.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "impretec_admin",
    ) {
        App()
    }
}