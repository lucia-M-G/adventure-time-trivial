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
        Pregunta("facil1", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil2", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil3", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil4", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil5", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil6", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil7", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil8", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil9", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
        Pregunta("facil10", listOf("respuesta1", "respuesta2", "respuesta3", "respuesta4"), 0, 1),
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
    fun cambiarDificultad(dif: Int): String {
        val currentMenu = _valorMenu.value
        _valorMenu.value = currentMenu.copy(
            dificultad = dif
        )
        when(dif) {
            1 -> return "Facil"
            2 -> return "Normal"
            3 -> return "Dificil"
        }
        return ""
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
}