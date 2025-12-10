package com.example.trivial.viewmodel

import android.R
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trivial.model.Game
import com.example.trivial.model.Launch
import com.example.trivial.model.Menu
import com.example.trivial.model.Pregunta
import com.example.trivial.model.Result

class TrivialViewModel: ViewModel() {
    var preguntas = listOf(
        // Dificulatd fácil
        Pregunta("¿Cómo se llama el mejor amigo y hermano adoptivo de Finn?", listOf("BMO", "Marceline", "Jake", "Gunter"), 2, 1),
        Pregunta("¿Qué objeto utiliza Finn como arma principal?", listOf("Un martillo", "Una espada", "Un palo mágico", "Un arco"), 1, 1),
        Pregunta("¿Cuál es el nombre del reino gobernado por la Princesa Chicle?", listOf("Reino de Hielo", "Chuchelandia", "Reino de Fuego", "Espacio Bultos"), 1, 1),
        Pregunta("¿Qué es BMO?", listOf("Una consola", "Un humano", "Un pero mágico", "Un vampiro"), 0, 1),
        Pregunta("¿Quién es el villano conocido por secuestrar princesas?", listOf("Orgalorg", "El Rey Hielo", "Ricardio", "Ash"), 1, 1),
        Pregunta("¿De qué color es principalmente Jake?", listOf("Amarillo", "Verde", "Azul", "Naranja"), 0, 1),
        Pregunta("¿Cómo se llama la princesa hecha de fuego?", listOf("Princesa Fantasma", "Princesa Mora", "Princesa Llama", "Princesa Salchicha"), 2, 1),
        Pregunta("¿Qué instrumento toca Marceline?", listOf("El violín", "La guitarra acústica", "El bajo", "El piano"), 2, 1),
        Pregunta("¿Qué es Finn?", listOf("Un perro", "Un mago", "Un ogro", "Un humano"), 3, 1),
        Pregunta("¿Quién gobierna el Reino de Hielo?", listOf("El Rey Hielo", "Billy", "Hunson Abadeer", "El Conde Limoncio"), 0, 1),
        // Dificultad normal
        Pregunta("normal1", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal2", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal3", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal4", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal5", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal6", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal7", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal8", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal9", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        Pregunta("normal10", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 2),
        // Dificultad difícil
        Pregunta("dificil1", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil2", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil3", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil4", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil5", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil6", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil7", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil8", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil9", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
        Pregunta("dificil10", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 3),
    )
    private val _valorLaunch = MutableLiveData(Launch(3))
    private val _valorMenu = MutableLiveData(Menu(1))
    private var _valorGame = MutableLiveData(Game(1, preguntas[0], false, 0, 0))
    private val _valorResult = MutableLiveData(Result(0))

    val valorLaunch: LiveData<Launch> = _valorLaunch
    val valorMenu: LiveData<Menu> = _valorMenu
    val valorGame: LiveData<Game> = _valorGame
    val valorResult: LiveData<Result> = _valorResult

    fun generarPregunta() {
        val currentGame = _valorGame.value
        var currentMenu = _valorMenu.value
        currentGame.pregunta = preguntas.filter { it.dificultad == currentMenu.dificultad } [currentGame.ronda-1]
    }
    fun incrementarRonda() {
        val currentGame = _valorGame.value
        if (currentGame.ronda < 10) {
            _valorGame.value = currentGame.copy(
                ronda = currentGame.ronda + 1
            )
        }
    }
    fun resetearRonda() {
        val currentGame = _valorGame.value
        _valorGame.value = currentGame.copy(
            ronda = 1,
            puntuacion = 0
        )
    }
    fun cambiarDificultad(dif: Int) {
        val currentMenu = _valorMenu.value
        _valorMenu.value = currentMenu.copy(
            dificultad = dif
        )
    }

    fun determinarRespuesta(opcion: Int) {
        val currentGame = _valorGame.value
        if (currentGame.pregunta.indiceCorrecta == opcion) {
            _valorGame.value = currentGame.copy(
                puntuacion = currentGame.puntuacion + 10
            )
        }
    }

    fun cambiarColores(colores: MutableList<Color>): MutableList<Color> {
        val currentGame = _valorGame.value
        for (i in 0..<colores.size) {
            if (i == currentGame.pregunta.indiceCorrecta) {
                colores[i] = Color(0xFF00FF00)
            } else {
                colores[i] = Color(0xFFFF0000)
            }
        }
        return colores
    }

    fun actualizarPuntuacionFinal() {
        val currentGame = _valorGame.value
        _valorResult.value = Result(currentGame?.puntuacion ?: 0)
    }
}