package com.example.trivial.model

data class Game(
    var ronda: Int,
    var pregunta: Pregunta,
    var respuestaCorrecta: Boolean,
    var tiempoRestante: Int
)