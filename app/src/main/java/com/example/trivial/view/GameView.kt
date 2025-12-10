package com.example.trivial.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.viewmodel.TrivialViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.trivial.view.Routes
import kotlinx.coroutines.delay

@Composable
fun GameView(
    modifier: Modifier = Modifier,
    trivialViewModel: TrivialViewModel,
    navController: NavController
) {
    val estado by trivialViewModel.valorGame.observeAsState()

    var tiempoRestante by remember { mutableIntStateOf(30) }
    var tiempoActivo by remember { mutableStateOf(true) }
    var yaRespondio by remember { mutableStateOf(false) }

    // Temporizador
    LaunchedEffect(key1 = tiempoActivo, key2 = tiempoRestante) {
        while (tiempoActivo && tiempoRestante > 0) {
            delay(1000L)
            tiempoRestante--
        }
        if (tiempoRestante == 0) {
            tiempoActivo = false
            // Se acaba el tiempo = mostrar respuesta
            yaRespondio = true
        }
    }

    val ronda = estado?.ronda ?: 1
    val pregunta = estado?.pregunta
    val texto = pregunta?.pregunta ?: "Pregunta..."
    val respuestas = pregunta?.respuestas ?: listOf("", "", "", "")
    val correcta = pregunta?.indiceCorrecta ?: -1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ronda + Tiempo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Ronda $ronda/10",
                fontSize = 22.sp
            )

            // Temporizador
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { tiempoRestante / 30f },
                    modifier = Modifier.size(50.dp),
                    strokeWidth = 4.dp,
                    color = if (tiempoRestante < 10) Color.Red else Color.Gray
                )
                Text(
                    text = "$tiempoRestante",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta para las preguntas
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
        ) {
            Text(
                text = texto,
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 4 Botones, pero los he puesto en columna
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            respuestas.forEachIndexed { i, respuesta ->
                // Colores no funcionan
                val color = if (yaRespondio || tiempoRestante == 0) {
                    if (i == correcta) Color.Green else Color.Red.copy(alpha = 0.7f)
                } else {
                    Color.Unspecified
                }

                Button(
                    onClick = {
                        if (!yaRespondio && tiempoActivo) {
                            // Sumar puntos
                            trivialViewModel.determinarRespuesta(i)
                            tiempoActivo = false
                            yaRespondio = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !yaRespondio && tiempoActivo,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (color == Color.Unspecified) {
                            ButtonDefaults.buttonColors().containerColor
                        } else {
                            color
                        }
                    )
                ) {
                    Text(
                        text = respuesta,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón siguiente que aparece si hay respuesta o no queda tiempo
        if (yaRespondio || tiempoRestante == 0) {
            val esUltima = ronda >= 10

            Button(
                onClick = {
                    if (esUltima) {
                        navController.navigate(Routes.Result.route)
                    } else {
                        // Avanzar
                        trivialViewModel.incrementarRonda()
                        trivialViewModel.generarPregunta()
                        // Reiniciar estados
                        tiempoRestante = 30
                        tiempoActivo = true
                        yaRespondio = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                )
            ) {
                Text(
                    text = if (esUltima) "VER RESULTADOS" else "SIGUIENTE PREGUNTA",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}