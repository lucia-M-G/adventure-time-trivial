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
        Pregunta("¿Cómo consiguió Finn su famoso gorro blanco?", listOf("Se lo dio la Princesa Chicle", "Se lo fabricó él mismo", "Lo encontró en el bosque", "Fue un regalo de Jake"), 1, 2),
        Pregunta("¿Cuál es el verdadero nombre de la Princesa Chicle?", listOf("Bubbleline", "Bonnie Jean", "Bonnibel", "Belinda"), 2, 2),
        Pregunta("¿Qué relación tiene Marceline con Hunson Abadeer?", listOf("Es su hija", "Es su aprendiz", "Es su enemiga", "Es su hermana"), 0, 2),
        Pregunta("¿Qué elemento representa la Princesa Chicle?", listOf("Hielo", "Fuego", "Lodo", "Dulce"), 3, 2),
        Pregunta("¿Cómo se llama la espada roja usada por Finn?", listOf("La Espada de Hierro", "La Espada Demonio", "La Espada Nube", "La Espada Carmesí"), 3, 2),
        Pregunta("¿Cuál es la comida favorita de Finn?", listOf("La pizza", "El helado", "El pastel de carne", "Los burritos"), 2, 2),
        Pregunta("¿Cómo se llama el científico al que suele recurrir la Princesa Chicle?", listOf("Ricardio", "Magic Man", "Mayordomo Menta", "El Conde de Chicle"), 3, 2),
        Pregunta("¿Qué tipo de criatura es la Princesa del Espacio Bultos?", listOf("Una persona de bultos", "Un fantasma", "Una gelatina", "Una nube"), 0, 2),
        Pregunta("¿Cuál es el nombre del padre de Marceline?", listOf("Hudson Abadeer", "Hunson Abadeer", "Hans Abardeer", "Huston Abadeir"), 1, 2),
        Pregunta("¿Con qué personaje mantiene Finn una relación sentimental importante en la serie?", listOf("Princesa Llama", "Marceline", "Princesa Hielo", "Princesa Tortuga"), 0, 2),
        // Dificultad difícil
        Pregunta("¿Cómo se llama el episodio en el que se muestra el pasado de Simon y Marcy?", listOf("Betty", "Islas", "Simon y Marcy", "Te Recuerdo"), 2, 3),
        Pregunta("¿Qué objeto provoca que la Espada Dorada se destruya y evolucione en otra?", listOf("La corona del Rey Hielo", "El guante de Billy", "La explosión de un portal", "Una gema maldita"), 3, 3),
        Pregunta("¿Qué es Orgalorg en realidad?", listOf("Un destructor interestelar", "Un antiguo mago", "Un demonio de fuego", "Un elemental del hielo"), 0, 3),
        Pregunta("¿Cómo se llama el “hermano” de Limoncio?", listOf("Limoncio Junior", "Limoncio 2", "Limoncín", "Lemonjon"), 0, 3),
        Pregunta("¿Quiénes son los creadores originales del mundo de Ooo?", listOf("Los magos antiguos", "Los humanos", "Los Elementales", "Los guardianes del tiempo"), 2, 3),
        Pregunta("¿Qué rasgo especial se revela sobre el linaje de Finn en la miniserie 'Islas'?", listOf("Que no envejecen", "Que tienen afinidad mágica", "Que pueden respirar bajo el agua", "Que son exploradores por naturaleza"), 3, 3),
        Pregunta("¿Cómo se llama la espada poseída por un espíritu demoníaco?", listOf("La Espada Demonio", "La Espada de Hierro Negro", "La Espada Estigia", "La Espada Abismo"), 0, 3),
        Pregunta("¿En qué episodio se muestra parte del mundo antes del apocalipsis que creó Ooo?", listOf("Evergreen", "La Bóveda", "Everything Stays", "Reacción Mortal"), 2, 3),
        Pregunta("¿Qué intenta hacer Betty con respecto a la corona del Rey Hielo?", listOf("Destruirla completamente", "Revertir su magia para salvar a Simon", "Usarla para viajar en el tiempo", "Tomar su poder para ella"), 1, 3),
        Pregunta("¿Cuál es el nombre del ser cósmico que aparece en el final de la serie?", listOf("Maja", "El Lich", "Cthulion", "GOLB"), 3, 3),
    )
    private val _valorLaunch = MutableLiveData(Launch(3))
    private val _valorMenu = MutableLiveData(Menu(1))
    private var _valorGame = MutableLiveData(Game(1, preguntas[0], false, 0))
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