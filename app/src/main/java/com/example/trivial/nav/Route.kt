package com.example.trivial.view

sealed class Routes(val route: String) {
    object Launch: Routes("launch")
    object Menu: Routes("menu")
    object Game: Routes("game")
    object Result: Routes("result")
}