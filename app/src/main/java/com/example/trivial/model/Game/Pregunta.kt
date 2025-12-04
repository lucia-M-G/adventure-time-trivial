package com.example.trivial.model

data class Pregunta(
    val pregunta: String,
    val respuestas: List<String>,
    val indiceCorrecta: Int,
    val difucltad: Int
)