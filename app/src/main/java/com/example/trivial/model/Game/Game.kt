package com.example.trivial.model

data class Game(
    var ronda: Int,
    var pregunta: String,
    var respuestaCorrecta: Boolean,
    var tiempoRestante: Int
)